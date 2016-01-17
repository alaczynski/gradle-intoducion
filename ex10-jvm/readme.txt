1. no daemon, no parallel
gradle clean build
gradle --daemon

2. daemon, no parallel
gradle --daemon clean build

3. daemon, parallel
gradle --daemon clean build --parallel