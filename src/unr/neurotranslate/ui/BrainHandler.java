package unr.neurotranslate.ui;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.Window;
import org.gnome.gtk.Button.Clicked;

import unr.neurotranslate.ncs.NCSData;

public class BrainHandler {
	
	public TreeView columnView;
	public TreeView stimulusView;
	public TreeView reportsView;
	public TreeView connectionView;
	
	// All array lists are for debugging!
	public ArrayList<String> actBrainColumnList;
	public ArrayList<String> availBrainColumnList;
	public ArrayList<String> brainStimulusList;
	public ArrayList<String> brainConnectionList;
	public ArrayList<String> brainReportsList;
	public ArrayList<String> activeL;		
	public ArrayList<String> availL;		
	
	public BrainHandler() throws FileNotFoundException {
		
		// Create array lists
		activeL = new ArrayList<String>();
		availL = new ArrayList<String>();
		actBrainColumnList = new ArrayList<String>();
		availBrainColumnList = new ArrayList<String>();
		brainStimulusList = new ArrayList<String>();
		brainReportsList = new ArrayList<String>();
		brainConnectionList = new ArrayList<String>();
		
		setLists(); 	
		
		setEntries();

		modifyHandler();		
	}
	
	public void setLists() throws FileNotFoundException {

		// Build models and set views		
		WidgetReferences.columnTypes = new ListEntity( actBrainColumnList, "bColTypes", "window1" ); // debugging
		ListEntity stimulusInjects = new ListEntity( brainStimulusList, "bStimInjects", "window1" );
		ListEntity reports = new ListEntity( brainReportsList, "bReports", "window1" );
		ListEntity connections = new ListEntity( brainConnectionList, "bConnections", "window1" );
		
		
		// TODO - Get array lists from data model
		availBrainColumnList.add("visual_cortex");
		availBrainColumnList.add("hypothalamus");
		availBrainColumnList.add("parietal_cortex");		

		brainStimulusList.add("visual");
		brainStimulusList.add("proprioceptive");	
	
		brainReportsList.add("hypothalamus_learning");
		brainReportsList.add("visual_voltage");
		brainReportsList.add("parietal_voltage");
	
		brainConnectionList.add("visual_hypothalamus");
		brainConnectionList.add("visual_parietal");	
		brainConnectionList.add("parietal_hypothalamus");
	}
	
	public void setEntries () throws FileNotFoundException {
		
		// Grab all entry widgets
		Entry brainType = (Entry) GladeParseUtil.grabWidget( "brainType", "window1" );
		Entry brainJob = (Entry) GladeParseUtil.grabWidget( "brainJob", "window1" );
		Entry brainFSV = (Entry) GladeParseUtil.grabWidget( "brainFSV", "window1" );
		Entry brainDuration = (Entry) GladeParseUtil.grabWidget( "brainDuration", "window1" );
		Entry brainSeed = (Entry) GladeParseUtil.grabWidget( "brainSeed", "window1" );
		
		// Set all entry widgets		
		brainType.setText("");					
		brainJob.setText("");
		brainFSV.setText( "" );		
		brainDuration.setText( "" );
		brainSeed.setText( ""  );
	}
	
	public void modifyHandler() throws FileNotFoundException {
		
		// Grab all button and window widgets
		Button modifyColumn = (Button)  GladeParseUtil.grabWidget( "bModColumn", "window1" );
		Button modifyStimulus = (Button)  GladeParseUtil.grabWidget( "brainAddStimulus", "window1" );
		Button modifyeport = (Button)  GladeParseUtil.grabWidget( "brainAddReport", "window1" );
		Button modifyConnection = (Button) GladeParseUtil.grabWidget( "brainAddConnection", "window1" );
		final Window popup = (Window) GladeParseUtil.grabWidget( "window2", "window2" );
		final Window main = (Window)GladeParseUtil.grabWidget( "window1", "window1" );
		
		// Set up connect for modifying a column
		modifyColumn.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
						
				// Show popup and update the views
				popup.show();
				ModifyPopup.updateViews( "Column Types", actBrainColumnList, availBrainColumnList );		
			}
		});	
		
		// Set up connect for modifying a stimulus inject
		modifyStimulus.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				// Show popup and update the views
				popup.show();
				ModifyPopup.updateViews( "Stimulus Injects", brainStimulusList, brainStimulusList );
				
			}
		});

	}
}