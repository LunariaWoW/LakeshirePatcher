# LakeshireRealmPatcher
Transferiert WoW Addonprofile vom Realm "Lakeshire" nach "Seenhain"

Durch die Umstellung des Realmnamens von Lakeshire zu Seenhain, hat das Auswirkungen auf alle Addons die Profile nutzen, bzw den Charakter mit Realmnamen speichern.

# Was macht der Patcher?

1. Der Patcher erstellt zuerst Backups der Ordner "Lakeshire"  (Charaktereinstellungen) und dem Ordner SavedVariables (Globale Einstellungen)
2. Nach dem Backup wird der Ordner Lakeshire kopiert und in Seehain umbenannt
3. Das Script geht durch alle LUA Dateien, welche für die Einstellungen genutzt werden und ersetzt den Text "Lakeshire" durch "Seenhain"

Der Patcher funktioniert evtl nicht zu 100%, aber sollte das meiste beheben.
Probleme könnten z.B. auftreten, wenn eure Gilde oder Spieler den Text "Lakeshire" im Namen besitzen.

# Patcher ausführen:

Der Patcher benötigt zur Ausführung Java.
1. Zip entpacken
2. Die .jar und .bat Datei verschieben: ...\World of Warcraft\_classic_\WTF\Account\123456789#1 (Die Zahl ist je nach Acc unterschiedlich)
In dem gleichen Ordner sind auch die beiden Ordner "SavedVariables" und "Lakeshire"
3. patcher.bat ausführen
4. Beide Dateien können danach gelöscht werden

**Bitte vor der Nutzung ein Backup eures WoW Ordners erstellen. Nutzung auf eigene Gefahr.**

Verzeichnis:
![Verzeichnis](https://i.imgur.com/KgzUkjK.png)

Patcher:
![Patcher](https://i.imgur.com/CH8uevq.png)

Vorher - Nachher:
![Vergleich](https://i.imgur.com/QPJFFQS.jpg)
