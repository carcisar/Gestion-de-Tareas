package com.cuaderno.board.client;


import com.cuaderno.board.dto.TaskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "cuaderno-task-service", url = "http://cuaderno-task-service")
public interface TaskClient {

    @GetMapping("/tasks/byIds")
    List<TaskDTO> getTasksByIds(@RequestParam("ids") List<Long> ids);
}
