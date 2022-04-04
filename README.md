Alfred Consiglio
46170596
Server: 
//runs through sample-config01
./ds-server -v all -n -c ds-sample-config01.xml
//runs through config demos 00-08 returns stage1.diff
./demoS1.sh MyClient.class -v all -n
Client:
java MyClient.class -a bf -c ds-system.xml
