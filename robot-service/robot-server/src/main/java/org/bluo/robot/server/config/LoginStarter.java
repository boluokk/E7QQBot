package org.bluo.robot.server.config;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.auth.BotAuthorization;
import net.mamoe.mirai.utils.BotConfiguration;
import org.boluo.entity.QQUser;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 机器人登录
 *
 * @author boluo
 * @date 2023/11/12
 */
@Component
@Slf4j
public class LoginStarter {
    @Bean
    public Bot bot() {
        String configBean = System.getProperty("user.dir") + "\\botConfig.json";
        QQUser qqUser = null;
        try {
            qqUser = JSONUtil.toBean(FileUtil.readString(new File(configBean),
                            CharsetUtil.CHARSET_UTF_8),QQUser.class);
            log.warn("机器人配置文件内容: {}", qqUser);
        } catch (Exception e) {
            log.warn("解析botConfig.json文件错误");
        }
        Bot bot = BotFactory.INSTANCE.newBot(qqUser.getNumber(), BotAuthorization.byQRCode(), configuration -> {
            configuration.setProtocol(BotConfiguration.MiraiProtocol.ANDROID_WATCH);
        });
        new Thread(bot::login).start();
        return bot;
    }


}
