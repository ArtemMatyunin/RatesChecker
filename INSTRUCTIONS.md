#Run whit java command
##java -jar build/libs/RatesChecker-0.0.1-SNAPSHOT.jar
started on localhost:8080

#Run in docker command
##docker build --tag=rateschecker:latest .
##docker run --name rateschecker  --rm -p8081:8080 rateschecker:latest
started on localhost:8081

#Description
В задании сказано, что курс валюты нужно смотреть относительно рубля. 
Но мой уровень аккаунта в openexchangerates.com не позволяет менять базовую валюту.
По умолчанию это доллар. Поэтому, если смотреть с моим ID_APP, то потребуется заменить
в application.properties openexchangerates.base на USD.
Чтобы смотреть по рублю, потребуется ID_APP приложения, уровень аккаунта которого 
позволяет менять базовую валюту.