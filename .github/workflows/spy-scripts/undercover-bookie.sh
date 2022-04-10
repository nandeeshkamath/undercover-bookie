#!/bin/sh

# Wake up app
curl --location --request GET "$DOMAIN_URL/actuator/health" \
--header "Authorization: Basic $SECRET_TOKEN" \
--header 'Content-Type: application/json'

# Wait for boot up
sleep 15

# Check for ticket availability

curl --location --request GET "$DOMAIN_URL/spy?eventId=ET00310791&regionCode=MANI&regionSlug=manipal&keyword=Book%20tickets" \
--header "Authorization: Basic $SECRET_TOKEN" \
--header 'Content-Type: application/json'
