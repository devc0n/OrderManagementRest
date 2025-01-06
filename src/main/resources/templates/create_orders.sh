#!/bin/bash

for i in {1..100}
do
  curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{
    "customerName": "Customer'"$i"'",
    "product": "Product'"$((i % 5 + 1))"'",
    "quantity": '"$((RANDOM % 3 + 1))"',
    "price": '"$((RANDOM % 1000 + 100))"',
    "status": "PLACED"
  }'
  echo "Order $i created"
done
