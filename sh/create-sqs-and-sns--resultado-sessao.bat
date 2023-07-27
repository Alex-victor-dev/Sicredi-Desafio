aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name resultado-sessao-queue --profile localstack

aws --endpoint-url=http://localhost:4566 sns create-topic --name resultado-sessao-topic --profile localstack

aws --endpoint-url=http://localhost:4566 sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:resultado-sessao-topic --protocol sqs --notification-endpoint arn:aws:sqs:us-east-1:000000000000:resultado-sessao-queue --attributes RawMessageDelivery=true --profile localstack