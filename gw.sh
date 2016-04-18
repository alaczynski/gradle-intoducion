#!/bin/bash

FOUND=0
CURR_PATH="$PWD"
REAL_GRADLEW="$CURR_PATH/gradlew"

if [ -x "$REAL_GRADLEW" ]
then
FOUND=1
else
while [ "$CURR_PATH" != "/" ]
do
CURR_PATH=$(dirname "$CURR_PATH")
REAL_GRADLEW="$CURR_PATH/gradlew"

if [ -x "$REAL_GRADLEW" ]
then
FOUND=1
break
fi
done
fi

if [ $FOUND -eq 1 ]
then
$REAL_GRADLEW "$@"
else
echo "Unable to find gradlew file upwards in filesystem"
fi

exit 0
