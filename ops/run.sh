#bin/bash
rm -rf test-server/plugins/TwitchBridge.jar
cp target/TwitchBridge.jar test-server/plugins/TwitchBridge.jar
cd test-server
java -DIReallyKnowWhatIAmDoingISwear=true -jar spigot-1.16.5.jar nogui