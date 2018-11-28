# 版本信息
FROM docker.io/rabbitmq:3.7.8

# 维护者信息
MAINTAINER feifei.liu feifei.work@foxmail.com

# 标签信息
LABEL library-rabbitmq="3.7.8"

RUN rabbitmq-plugins enable --offline rabbitmq_mqtt rabbitmq_federation_management rabbitmq_management rabbitmq_stomp rabbitmq_web_stomp rabbitmq_web_stomp_examples

## 创建镜像
# docker build -t library/rabbitmq:3.7.8 .

## 启动容器
# docker run -d --name library-rabbitmq -p 5672:5672 -p 15672:15672 -p 25672:25672 -p 15674:15674 -p 15670:15670 -p 1883:1883 -p 61613:61613 -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin library/rabbitmq:3.7.8