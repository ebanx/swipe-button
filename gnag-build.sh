#!/bin/bash
set -ev

if [ "${TRAVIS_PULL_REQUEST}" = "false" ]; then
    ./gradlew gnagCheck --no-daemon
else
    ./gradlew gnagReport -PauthToken="${GNAG_AUTH_TOKEN}" -PissueNumber="${TRAVIS_PULL_REQUEST}" --no-daemon
fi


