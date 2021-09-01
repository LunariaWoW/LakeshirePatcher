import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class Main {

	static int fileCount = 0;
	static int filesPatched = 0;
	static String inputOldRealm = "";
	static String inputNewRealm = "";
	
	public static void main(String[] args) {
		final String PATH_STR = System.getProperty("user.dir") + File.separator;
		final Scanner SCANNER = new Scanner(System.in);
		System.out.println("RealmPatcher v3.0 gestartet");
		System.out.println("Lúnaria <Ritter der Kokosnuss> @ Lakeshire / Seenhain");
		System.out.println("");
		
		System.out.println("Folgender Pfad wird verwendet: " + PATH_STR);
		while (inputOldRealm.isEmpty()) {
			System.out.print("Alten Realm eingeben (z.B. Lakeshire): ");
			inputOldRealm = SCANNER.nextLine();
		}
		while (inputNewRealm.isEmpty()) {
			System.out.print("Neuen Realm eingeben (z.B. Seenhain): ");
			inputNewRealm = SCANNER.nextLine();
		}
		System.out.println("************************************************************");
		
		System.out.println("Einstellungen:");
		System.out.println("Alter Realm: " + inputOldRealm);
		System.out.println("Neuer Realm: " + inputNewRealm);
		System.out.println("");
		System.out.println("Beliebige Taste zum Starten drücken...");
		SCANNER.nextLine();
		
		File folderSavedVariables = new File(PATH_STR + "SavedVariables");
		File folderOldRealm = new File(PATH_STR + inputOldRealm);
		File folderNewRealm = new File(PATH_STR + inputNewRealm);
		
		// Alten Realmn kopieren und in neuen Realm umbenennen
		boolean error = true;
		while (error) {
			error = false;
			if (folderOldRealm.exists()) {
				if (!folderNewRealm.exists()) {
					System.out.println(String.format("Ordner '%s' wird kopiert und in '%s' umbenannt...", inputOldRealm, inputNewRealm));
					try {
						FileUtils.copyDirectory(folderOldRealm, folderNewRealm);
						System.out.println("   Done!");
					} catch (IOException e) {
						System.out.println("   Fehler beim Patchen!");
						e.printStackTrace();
						System.out.println("Der Patcher wird nun beendet...");
						System.exit(1);
					}
				} else {
					System.out.println(String.format("Fehler: Ordner des neuen Realms '%s' existiert bereits, alter Realm kann nicht umbenannt werden - Abbruch", inputNewRealm));
					System.out.print(String.format("Den Ordner '%s' löschen und eine beliebige Taste drücken...", inputNewRealm));
					SCANNER.nextLine();
					error = true;
				}
			} else {
				System.out.println(String.format("Fehler: Ordner des alten Realms '%s' existiert nicht - Abbruch", inputOldRealm));
				System.out.println("Der Patcher wird nun beendet...");
				System.exit(1);
			}
		}
		SCANNER.close();
		if (folderSavedVariables.exists()) patchFiles(folderSavedVariables, "Dateien im Ordner 'SavedVariables' werden gesammelt und gepatcht (Globale Einstellungen)");
		if (folderNewRealm.exists()) patchFiles(folderNewRealm, String.format("Dateien im Ordner '%s' werden gesammelt und gepatcht (Charakterbezogene Einstellungen)", inputNewRealm));
		
		System.out.println("");
		System.out.println("Fertig - " + fileCount + " Dateien durchsucht - " + filesPatched + " Dateien gepatcht!");
	}
	
	public static void patchFiles(File folder, String msg) {
		System.out.println("");
		System.out.println(msg);
		
		Iterator<File> iter = FileUtils.iterateFiles(folder, new String[]{"lua"}, true);
		while (iter.hasNext()) {
			fileCount += 1;
			File file = iter.next();
			try {
				String content = FileUtils.readFileToString(file);
				String contentPatched = content.replace(inputNewRealm, "Entfernt").replace(inputOldRealm, inputNewRealm);
				FileUtils.writeStringToFile(file, contentPatched);
				if (!Objects.equals(content, contentPatched)) {
					filesPatched += 1;
					System.out.println("Patched: " + file.getAbsolutePath());
				} else {
					System.out.println("Skipped: " + file.getAbsolutePath());
				}
				
			} catch (IOException e) {
				System.out.println("Fehler beim Patchen der Dateien!");
				e.printStackTrace();
			}
		}
	}

}
