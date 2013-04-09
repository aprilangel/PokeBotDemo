#!/bin/sh

echo "Création de la base de données pour le projet PokeBotDemo"

mysql -u root -pmysql --execute="CREATE DATABASE PokebattleDB"
