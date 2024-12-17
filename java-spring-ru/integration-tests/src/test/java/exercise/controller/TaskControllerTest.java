package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    private Task generateTask() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
    }

    @Test
    public void testShow() throws Exception {
        var task = generateTask();
        taskRepository.save(task);
        var taskId = task.getId();

        var result = mockMvc.perform(get("/tasks/" + taskId))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).node("id").isEqualTo(taskId);
        assertThatJson(body).node("title").isEqualTo(task.getTitle());
        assertThatJson(body).node("description").isEqualTo(task.getDescription());
    }

    @Test
    public void testCreate() throws Exception {
        var taskTitle = faker.lorem().word();
        var taskDescription = faker.lorem().paragraph();

        var data = new HashMap<>();
        data.put("title", taskTitle);
        data.put("description", taskDescription);

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));

        mockMvc.perform(request)
                .andExpect(status().isCreated());

        var task = taskRepository.findByTitle(taskTitle).get();
        assertThat(task.getTitle()).isEqualTo(taskTitle);
        assertThat(task.getDescription()).isEqualTo(taskDescription);
    }

    @Test
    public void testUpdate() throws Exception {
        var task = generateTask();
        taskRepository.save(task);
        var taskId = task.getId();

        var updatedTaskTitle = faker.lorem().word();
        var updatedTaskDescription = faker.lorem().paragraph();

        var data = new HashMap<>();
        data.put("title", updatedTaskTitle);
        data.put("description", updatedTaskDescription);

        var request = put("/tasks/" + taskId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        var updatedTask = taskRepository.findById(taskId).get();
        assertThat(updatedTask.getTitle()).isEqualTo(updatedTaskTitle);
        assertThat(updatedTask.getDescription()).isEqualTo(updatedTaskDescription);
    }

    @Test
    public void testDelete() throws Exception {
        var task = generateTask();
        taskRepository.save(task);
        var taskId = task.getId();

        mockMvc.perform(delete("/tasks/" + taskId))
                .andExpect(status().isOk());

        assertThat(taskRepository.findById(taskId).orElse(null)).isNull();
    }
    // END
}
