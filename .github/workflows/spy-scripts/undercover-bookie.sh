#!/bin/sh

curl --location --request POST "$DOMAIN_URL/spy?eventId=ET00094579&regionCode=KUNA&regionSlug=kundapura&keyword=Book%20tickets" \
--header "Authorization: Basic $SECRET_TOKEN" \
--header 'Content-Type: application/json'
