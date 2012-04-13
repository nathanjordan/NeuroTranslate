package unr.neurotranslate.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.gnome.gdk.EventVisibility;
import org.gnome.gdk.VisibilityState;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.Notebook;
import org.gnome.gtk.ScrolledWindow;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Button.Clicked;

import unr.neurotranslate.ncs.Report;
import unr.neurotranslate.ui.controller.UIControllerNCS;

public class ReportHandler {
	// All array lists are for debugging
	public ArrayList<String> rrep;
	
	public ListEntity reports;
	public ComboEntity colSel;
	public ComboEntity laySel;
	public ComboEntity cellSel;
	public ComboEntity compSel;	
	public Entry repType;
	public Entry repProb;
	public Entry repFreq;
	public Entry repCellSeq;
	public Entry repReportOn;
	public Entry repFilename;
	public Entry repTStart;
	public Entry repTEnd;
	public Report currentReport;
	
	public ReportHandler(WidgetReferences w, UIControllerNCS ui) throws FileNotFoundException {
		
	
		// Data sources
		rrep = new ArrayList<String>();
		
		// Entries
		repType = (Entry) GladeParseUtil.grabWidget( "entry79", "window1" );
		repProb = (Entry) GladeParseUtil.grabWidget( "entry84", "window1" );
		repFreq = (Entry) GladeParseUtil.grabWidget( "entry85", "window1" );
		repCellSeq = (Entry) GladeParseUtil.grabWidget( "entry80", "window1" );
		repReportOn = (Entry) GladeParseUtil.grabWidget( "entry81", "window1" );
		repFilename = (Entry) GladeParseUtil.grabWidget( "entry82", "window1" );
		repTStart = (Entry) GladeParseUtil.grabWidget( "entry83", "window1" );
		repTEnd = (Entry) GladeParseUtil.grabWidget( "entry87", "window1" );
		
		// Lists
		reports = new ListEntity( "ReportsList", "window1" );		
		colSel = new ComboEntity( "combobox9", "window1" );
		laySel = new ComboEntity( "combobox13", "window1" );
		cellSel = new ComboEntity( "combobox14", "window1" );
		compSel = new ComboEntity( "combobox15", "window1" );
		
		setLists();
		
		setEntries();
		
		modifyHandlers();
		
	}
	
	public void setLists() throws FileNotFoundException {
	
		
				
	}
	
	public void setEntries() throws FileNotFoundException {
	
		
	}
	
	public void modifyHandlers() throws FileNotFoundException {
	
		//Buttons for add/removing
		Button addReport = (Button) GladeParseUtil.grabWidget( "rRepAdd", "window1" );
		Button remReport = (Button) GladeParseUtil.grabWidget( "rRepRem", "window1" );
		
		// Connect for adding a Layer Shell
		addReport.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				reports.addData( "new report");
				
			}
		});		
		
		// Connect for removing a Layer Shell
		remReport.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				reports.removeData( );
				
			}
		});	
		
	}
}
