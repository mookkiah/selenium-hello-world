#!/bin/sh

# https://www.selenium.dev/documentation/en/grid/grid_4/setting_up_your_own_grid/

# Run using Selenium Standalone Server 
#java -jar selenium-server.jar standalone


# Run in Hub and Node
kill $(ps aux | grep 'selenium-server.jar' | awk '{print $2}')
java -jar selenium-server.jar hub > hub.log  &
java -jar selenium-server.jar node --detect-drivers true > node.log &
tail -f node.log hub.log


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
