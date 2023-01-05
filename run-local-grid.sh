#!/bin/sh

# https://www.selenium.dev/documentation/en/grid/grid_4/setting_up_your_own_grid/

JVM_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005

# Run using Selenium Standalone Server 
#java -jar selenium-server.jar standalone


## Run in Hub and Node
#kill $(ps aux | grep 'selenium-server.jar' | awk '{print $2}')
#java -jar selenium-server.jar hub > hub.log  &
#java -jar selenium-server.jar node --detect-drivers true > node.log &
#tail -f node.log hub.log


# # Distributed Mode
# kill $(ps aux | grep 'selenium-server.jar' | awk '{print $2}')
# java -jar selenium-server.jar event-bus > event-bus.log &
# sleep 5
# java -jar selenium-server.jar sessions > sessions.log &
# sleep 5
# java -jar selenium-server.jar sessionqueuer > sessionqueuer.log &
# sleep 5
# java -jar selenium-server.jar distributor --sessions http://localhost:5556 --sessionqueuer http://localhost:5559 --bind-bus false > distributor.log  &
# java -jar selenium-server.jar router --sessions http://localhost:5556 --distributor http://localhost:5553 --sessionqueuer http://localhost:5559 > router.log &
# java -jar selenium-server.jar node --detect-drivers true > node.log &
# tail -f event-bus.log sessions.log sessionqueuer.log distributor.log router.log node.log


# Distributed Mode with Docker Node
#kill $(ps aux | grep 'selenium-server.jar' | awk '{print $2}')
#java -jar selenium-server.jar event-bus > event-bus.log &
#sleep 5
#java -jar selenium-server.jar sessions > sessions.log &
#sleep 5
#java -jar selenium-server.jar sessionqueuer > sessionqueuer.log &
#sleep 5
#java -jar selenium-server.jar distributor --sessions http://localhost:5556 --sessionqueuer http://localhost:5559 --bind-bus false > distributor.log  &
#java -jar selenium-server.jar router --sessions http://localhost:5556 --distributor http://localhost:5553 --sessionqueuer http://localhost:5559 > router.log &
#java -jar selenium-server.jar node -D selenium/standalone-firefox:latest '{"browserName": "firefox"}' --detect-drivers false > firefox-node.log &
##java -jar selenium-server.jar node -D selenium/standalone-chrome:latest '{"browserName": "chrome"}' --detect-drivers false > chrome-node.log &
##tail -f event-bus.log sessions.log sessionqueuer.log distributor.log router.log node.log
#

# Standalone server with docker nodes (Not working: TODO: try with other browser node image then remote debug)
kill $(ps aux | grep 'selenium-server.jar' | awk '{print $2}')
java $JVM_OPTS -jar selenium-server.jar standalone -D selenium/standalone-firefox:latest '{"browserName": "firefox"}' --detect-drivers false

# Tried this command - running in windows VM (in parallel) by pointing out host(Mac) docker. No Luck
# java -DDOCKER_HOST=tcp://mm-lab:2375 -jar selenium-server.jar standalone -D selenium/standalone-firefox:latest '{"browserName": "firefox"}' --detect-drivers false



# Docker Way - Standalone Grid with one type of browser
#git clone https://github.com/SeleniumHQ/docker-selenium.git
#cd docker-selenium
docker run -d -p 4444:4444 -p 7900:7900 --shm-size 2g selenium/standalone-firefox:latest

# Access the grid http://localhost:4444/ui
# To VNC to the session (http://localhost:4444/ui#/sessions) the password is `secret`

# Start jaeger
docker run --rm -it --name jaeger \
    -p 16686:16686 \
    -p 14250:14250 \
    jaegertracing/all-in-one:1.19.2

java -Dotel.traces.exporter=jaeger \
       -Dotel.exporter.jaeger.endpoint=http://localhost:14250 \
       -Dotel.resource.attributes=service.name=selenium-standalone \
       -jar selenium-server.jar \
       --ext $(coursier fetch -p \
          io.opentelemetry:opentelemetry-exporter-jaeger:1.0.0 \
          io.grpc:grpc-netty:1.35.0) \
       standalone
