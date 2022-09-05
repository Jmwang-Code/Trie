package com.cn.jmw.config;

import cn.hutool.core.util.StrUtil;
import com.cn.jmw.base.R;
import com.cn.jmw.exception.BizException;
import com.cn.jmw.exception.code.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

/**
 * @author zhuwukai
 * @date 2021.12.07
 */
@Component
@Slf4j
public class KafkaService {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String data) throws BizException {
        if (StrUtil.isNotEmpty(topic)) {
            kafkaTemplate.send(topic, data);
        } else if (StrUtil.isNotEmpty(kafkaTemplate.getDefaultTopic())) {
            kafkaTemplate.sendDefault(data);
        } else {
            throw new BizException(ExceptionCode.KAFKA_TOPIC_MISSING.getCode(), ExceptionCode.KAFKA_TOPIC_MISSING.getMsg());
        }
    }


    public R sendCallback(String topic, String data) {
        if (StrUtil.isNotEmpty(topic)) {
            kafkaTemplate.send(topic, data).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    //成功的处理
                    log.info(topic + " - 生产者 发送消息成功：" + data);
                }

                @Override
                public void onFailure(Throwable ex) {
                    //发送失败的处理
                    log.info(topic + " - 生产者 发送消息失败：" + data);
                }
            });
        } else {
            throw new BizException(ExceptionCode.KAFKA_TOPIC_MISSING.getCode(), ExceptionCode.KAFKA_TOPIC_MISSING.getMsg());
        }

        return R.success();
    }
}
