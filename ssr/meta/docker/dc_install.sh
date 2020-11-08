#!/bin/sh
  yum install docker -y \
  && systemctl enable docker \
  && systemctl start docker \
  && curl -L "https://github.com/docker/compose/releases/download/1.27.4/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose \
  && sudo chmod +x /usr/local/bin/docker-compose \
  && sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose \
  && echo \
  "version: \"3\"
services:
  shadowsocks:
    image: shadowsocks/shadowsocks-libev
    ports:
      - "29499:8388/tcp"
      - "29499:8388/udp"
    environment:
      - METHOD=aes-256-gcm
      - PASSWORD=poiu)(*&
    restart: always
  " > docker-compose.yml \
  && docker-compose up -d \
  && yum install -y wget \
  && wget --no-check-certificate -O /opt/bbr.sh https://github.com/teddysun/across/raw/master/bbr.sh \
  && chmod 755 /opt/bbr.sh \
  && /opt/bbr.sh \
  && rm -f ./* \
  && reboot