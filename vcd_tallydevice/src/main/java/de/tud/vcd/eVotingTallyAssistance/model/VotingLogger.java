package de.tud.vcd.eVotingTallyAssistance.model;

import java.util.ArrayList;

import de.tud.vcd.eVotingTallyAssistance.model.VotingLogEntry.LogLevel;


public class VotingLogger {

	private ArrayList<VotingLogEntry> logs;

	private static VotingLogger instance = null;

	/**
	 * liefert die Instanz des Singleton Pattern. So wird das Objekt nur einmal
	 * angelegt und verf�gt �ber das Wissen.
	 * 
	 * @return
	 */
	public static synchronized VotingLogger getInstance() {
		if (instance == null) {
			instance = new VotingLogger();
		}
		return instance;
	}

	/**
	 * Konstruktor legt die ArrayList an, wenn das Objekt noch nicht vorhanden
	 * war.
	 * 
	 */
	public VotingLogger() {
		logs = new ArrayList<VotingLogEntry>();
	}

	/**
	 * f�gt einen Logeintrag der Liste hinzu. Mit Level und Text
	 * 
	 * @param level
	 *            : Logginglevel der Botschaft
	 * @param msg
	 *            : die botschaft selber
	 */
	public void log(LogLevel level, String msg) {
		logs.add(new VotingLogEntry(level, msg));
	}

	/**
	 * leert die Liste komplett. Ist nur beim Start sinnvoll, wenn die Wahlurne
	 * initialisiert wird, um auch einen leeren Log zu haben.
	 */
	public void flush() {
		logs.clear();
	}

	/**
	 * f�gt einen Logeintrag der Liste hinzu nur mit Text.
	 * 
	 * @param msg
	 */
	public void log(String msg) {
		logs.add(new VotingLogEntry(LogLevel.INFO, msg));
	}

	/**
	 * Gibt die LoggingInformationen aus. Aber nur bis zu einem bestimmten
	 * Logginglevel. Also kleineren werden nicht ausgegeben.
	 * 
	 * @param level
	 *            : Logginglevel welches als Ausgabeschwelle dient.
	 * @return ArrayList of VotingLogEntries
	 */
	public ArrayList<VotingLogEntry> getVotingLogEntries(LogLevel level) {
		ArrayList<VotingLogEntry> res = new ArrayList<VotingLogEntry>();
		for (VotingLogEntry vle : logs) {
			if (vle.getLevel().ordinal() >= level.ordinal()) {
				res.add(vle);
			}
		}
		return res;
	}

	/**
	 * Gibt die LoggingInformationen als STRING_ARRAY aus. Aber nur bis zu einem
	 * bestimmten Logginglevel. Also kleineren werden nicht ausgegeben.
	 * 
	 * @param level
	 *            : Logginglevel welches als Ausgabeschwelle dient.
	 * @return ArrayList of Strings
	 */
	public ArrayList<String> toStringArray(LogLevel level) {
		ArrayList<String> res = new ArrayList<String>();
		for (VotingLogEntry vle : logs) {
			if (vle.getLevel().ordinal() >= level.ordinal()) {
				res.add(vle.toString());
			}
		}
		return res;
	}

}