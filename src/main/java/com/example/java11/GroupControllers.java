package com.example.java11;

import com.example.java11.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class GroupControllers {

    @Autowired
    private  GroupRepository groupRepository;

    @GetMapping("/get_group_by_name")
    @ResponseBody
    public String getGroupByName(@RequestParam String name){
        return groupRepository.getGroupByName(name).toString();
    }

    @GetMapping("/delete_group_by_name")
    @ResponseBody
    public String deleteGroupByName(@RequestParam String name){
        groupRepository.deleteGroupByName(name);
        return  name + " was successfully deleted";
    }

    @PostMapping("/post_group")
    @ResponseBody
    public String postStudent(@RequestBody Group group){
        groupRepository.addGroup(group);
        return "Success";
    }


    @GetMapping("/get_all_groups")
    @ResponseBody
    public List<Group> getAllStudent(){
        return groupRepository.getAllGroups();
    }
}
