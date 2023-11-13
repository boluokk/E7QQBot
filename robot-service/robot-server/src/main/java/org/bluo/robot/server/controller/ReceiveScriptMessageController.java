package org.bluo.robot.server.controller;

import org.bluo.robot.server.service.SendMessageService;
import org.boluo.utils.ResponseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author boluo
 * @date 2023/11/12
 */
@RestController
@RequestMapping("/api/script/message")
@Validated
public class ReceiveScriptMessageController {
    @Resource
    private SendMessageService sendMessageService;

    @PostMapping("/images")
    public ResponseEntity receiveImage(@NotNull(message = "图片base64不能为空")
                                       @Length(min = 1, message = "图片base64长度不能小于1")
                                       @RequestParam("image") String image,
                                       @NotNull(message = "消息内容不能为空")
                                       @Length(min = 1, max = 255, message = "消息内容长度必须在1-255之间")
                                       @RequestParam("info") String info,
                                       @NotNull(message = "消息接收者不能为空")
                                       @Min(value = 9999, message = "QQ长度必须在5-13之间")
                                       @RequestParam("to")
                                       long to) {
        if (sendMessageService.qqNotify(image, info, to)) {
            return ResponseEntity.success("发送成功");
        }
        return ResponseEntity.clientFailure("发送失败");
    }
}

