package org.bluo.robot.server.config;

import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.auth.BotAuthorization;
import net.mamoe.mirai.utils.BotConfiguration;
import org.boluo.entity.QQUser;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author boluo
 * @date 2023/11/12
 */
@Component
@Slf4j
public class LoginStarter {
    private static final QQUser qqUser = new QQUser(2090848711, "");

    @Bean
    public Bot bot() {
        Bot bot = BotFactory.INSTANCE.newBot(qqUser.getNumber(), BotAuthorization.byQRCode(), configuration -> {
            configuration.setProtocol(BotConfiguration.MiraiProtocol.ANDROID_WATCH);
        });
        new Thread(() -> {
            bot.login();
        }).start();
        return bot;
    }


}
