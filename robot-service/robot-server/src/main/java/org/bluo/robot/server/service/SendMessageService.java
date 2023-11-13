package org.bluo.robot.server.service;

import cn.hutool.core.codec.Base64;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.message.data.PlainText;
import net.mamoe.mirai.utils.ExternalResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 发送消息 业务层
 *
 * @author boluo
 * @date 2023/11/12
 */
@Service
public class SendMessageService {

    @Resource
    private Bot bot;

    public boolean qqNotify(String imageBase64, String info, long to) {
        byte[] decode = Base64.decode(imageBase64);
        Friend target = bot.getFriendOrFail(to);
        target.sendMessage(new PlainText(info).plus(target.uploadImage(ExternalResource.create(decode))));
        return true;
    }
}
