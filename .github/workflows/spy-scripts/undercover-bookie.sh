#!/bin/sh

curl --location --request GET "$DOMAIN_URL/spy?eventId=ET00094579&regionCode=KUNA&regionSlug=kundapura&keyword=Book%20tickets" \
--header "Authorization: Basic $SECRET_TOKEN" \
--header 'Content-Type: application/json'
