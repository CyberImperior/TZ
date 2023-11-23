# Архитектура приложения

## База данных

База данных состоит из 3 таблиц, **customer** (информация о клиентах),
**account** (информация о счетах) и **log_balance** (информация о транзакциях).

Таблица **customer** состоит из столбцов:
- **id**
- **username**
- **pin** 

Таблица ***account*** имеет связь с таблицей ***customer*** состоит из столбцов:
- **id**
- **account_number**
- **balance**
- **customer_id**

  Таблица ***log_balance*** тоже имеет связь с таблицей ***customer*** состоит из столбцов:
- **id**
- **from_account_number** - номер счета с которого совершается операция 
в случае с transfer, в случаях, когда операция withdraw или replenishment тот счет, с которого снимаются деньги или тот счет, 
который пополняется 
- **to_account_number** - счет куда переводятся деньги, если операция transfer, если операции withdraw или replenishment, поле становится null
- **amount** - сумма операции
- **operation_type** - тип операции(transfer, withdraw или replenishment), заносится в таблицу с помощью Enum
- **timestamp**
- **customer_id**

## Компоненты приложения

Приложение написано на Spring Boot 3.1.5, также в приложение 
были подключены следующие зависимости:
- Spring Web
- Spring Data JPA
- Postgres Driver
- Lombok
- H2
- Swagger

## Структура приложения
Приложения построено по шаблону MVC и состоит из следующих логических блоков:
- controllers
- dto
- entities
- exceptions
- initdatabase
- repositories
- services
- util
- test

# API

## Регистрация

Чтобы зарегистрироваться нужно отправить POST запрос на эндпоинт */registration*,
при этом пин код должен состоять из 4 цифр, пример тела запроса ниже.  

Пример тела запроса:
```javascript
{
  "username": "Dimon",
  "pin": 1111
}
```

При инициализации таблицы создаются клиенты с именами *"Denis"* и *"Ivan"*, поэтому пользователей с такими именами создать не получится.

## Операции со средствами

### Перевод (transfer)

Чтобы сделать перевод, нужно отправить POST запрос на эндпоинт */operation/transfer*, пример тела запроса ниже.

Пример тела запроса:
```javascript
{
  "username": "Denis",
  "pin": 2343,
  "accountNumber":  1292937931691,
  "accountNumberTo": 1292937931694,
  "amountOfOperation": 1000
}
```
Поля *username* и *pin* должны быть введены корректно, пример тела запроса ниже
- *accountNumber* - номер счета с которого осуществляется перевод
- *accountNumberTo* - номер счета на который нужно перевести
- *amountOfOperation* - сумма операции

### Снятие (withdraw)

Чтобы сделать снять средства, нужно отправить POST запрос на эндпоинт */operation/withdraw*, пример тела запроса ниже.

Пример тела запроса:
```javascript
{
  "username": "Denis",
  "pin": 2343,
  "accountNumber":  1292937931691,
  "amountOfOperation": 1000
}
```
Поля *username* и *pin* должны быть введены корректно
- *accountNumber* - номер счета с которого будут сняты средства
- *amountOfOperation* - сумма операции

### Пополнение (replenishment)

Чтобы пополнить счет, нужно отправить POST запрос на эндпоинт */operation/replenishment*, пример тела запроса ниже.

Пример тела запроса:
```javascript
{
  "username": "Denis",
  "pin": 2343,
  "accountNumber":  1292937931691,
  "amountOfOperation": 1000
}
```
Поля *username* и *pin* должны быть введены корректно
- *accountNumber* - номер счета, на который поступит пополнение
- *amountOfOperation* - сумма операции

## Создание счета (счетов)

Чтобы создать счет (счета) для клиента, нужно отправить POST запрос на эндпоинт */addAccount*, пример тела запроса ниже.

Пример тела запроса:
```javascript
{
  "username": "Denis",
  "pin": 2343,
  "amountOfAccounts": 3
}
```
Поля *username* и *pin* должны быть введены корректно
- *amountOfAccounts* - количество счетов, которое нужно создать

## Получение данных

### Данные о счетах клиента

Чтобы получить данные о счете (счетах) клиента, нужно отправить POST запрос на эндпоинт */showAccounts*, пример тела запроса ниже.

Пример тела запроса:
```javascript
{
  "username": "Denis",
  "pin": 2343
}
```
Поля *username* и *pin* должны быть введены корректно

В случае успешного запроса, контроллер вернет ответ следующего вида

Пример ответа на запрос:
```javascript
{
  "username": "Denis",
  "accounts": [
    {
      "accountNumber": 1292937931692,
      "balance": 100000000
    },
    {
      "accountNumber": 1340007304632,
      "balance": 0
    },
    {
      "accountNumber": 1292937931691,
      "balance": 8000
    }
  ]
}
```
### Данные о транзакциях клиента

Чтобы получить данные о транзакциях клиента, нужно отправить POST запрос на эндпоинт */showTransactions*, пример тела запроса ниже.

Пример тела запроса:
```javascript
{
  "username": "Denis",
  "pin": 2343
}
```
Поля *username* и *pin* должны быть введены корректно.

В случае успешного запроса, контроллер вернет ответ следующего вида.

Пример ответа на запрос:

```javascript
{
  "username": "Denis",
  "logs": [
    {
      "operationType": "REPLENISHMENT",
      "amount": 1000,
      "fromAccountNumber": 1292937931691,
      "toAccountNumber": null,
      "timestamp": "2023-11-23T09:50:34.462758"
    },
    {
      "operationType": "TRANSFER",
      "amount": 10000000,
      "fromAccountNumber": 1292937931691,
      "toAccountNumber": 1292937931694,
      "timestamp": "2023-11-23T09:51:23.710272"
    },
    {
      "operationType": "WITHDRAW",
      "amount": 1000,
      "fromAccountNumber": 1292937931691, 
      "toAccountNumber": null,
      "timestamp": "2023-11-23T09:51:43.739427"
    }
  ]
}
```

- *logs* - это массив транзакций клиента
- *operationType* - тип операции
- *fromAccountNumber* - номер счета с которого совершается операция
  в случае с transfer, в случаях, когда операция withdraw или replenishment тот счет, с которого снимаются деньги или тот счет,
  который пополняется
- *toAccountNumber* - счет куда переводятся деньги, если операция transfer, если операции withdraw или replenishment, поле становится null
