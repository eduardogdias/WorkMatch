az provider register --namespace Microsoft.Sql
az provider register --namespace Microsoft.Web
 
NAME="workmatch" 
RG="rg-$NAME" 
LOCATION="brazilsouth"
 
# SQL Server e Banco
SERVER_NAME="sqlserver-$NAME"
USERNAME="admsql"
PASSWORD="Fiap@2tdsvms"
DBNAME="db-$NAME"
 
#Plano e Web App
WEBAPP_NAME_API="webapp-$NAME"
WEBAPP_NAME_EMAIL="webapp-$NAME-email"
PLAN_NAME="plan-$NAME"
RUNTIME="JAVA:17-java17"
sku=F1


echo "Criando RG"
az group create --name $RG --location $LOCATION

echo "Criando Server e Banco"
az sql server create -l $LOCATION -g $RG -n $SERVER_NAME -u $USERNAME -p $PASSWORD --enable-public-network true &&
az sql db create -g $RG -s $SERVER_NAME -n $DBNAME --service-objective Basic --backup-storage-redundancy Local --zone-redundant false &&
az sql server firewall-rule create -g $RG -s $SERVER_NAME -n AllowAll --start-ip-address 0.0.0.0 --end-ip-address 255.255.255.255

echo "Criando Plano e Web App"
az appservice plan create -n $PLAN_NAME -g $RG --location $LOCATION --sku F1 --is-linux  &&
az webapp create -n $WEBAPP_NAME_API -g $RG -p $PLAN_NAME --runtime $RUNTIME #api
az webapp create -n $WEBAPP_NAME_EMAIL -g $RG -p $PLAN_NAME --runtime $RUNTIME #email

