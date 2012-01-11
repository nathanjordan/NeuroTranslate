package unr.neurotranslate.ui;

import java.util.ArrayList;

import org.gnome.gtk.AttachOptions;
import org.gnome.gtk.Box;
import org.gnome.gtk.Entry;
import org.gnome.gtk.HBox;
import org.gnome.gtk.Label;
import org.gnome.gtk.Notebook;
import org.gnome.gtk.Table;
import org.gnome.gtk.TextComboBoxEntry;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;

/**
 * The NetworkTab class is responsible for handling all the Network tab layout and widgets. A Table layout is used in order to hold all the widgets used
 * in this tab. 
 * @author nitish/kim
 */
public class NetworkTab {

	// Nested tab variables
	private Notebook nestedTabs; 
	
	// VBox containers for nested tabs
	private VBox popBox;
	private VBox projectionBox;
	private VBox inputBox;
	
	// Widgets for tabs
	private ArrayList<Widget> popWidgets;
	private ArrayList<Widget> projWidgets;
	private ArrayList<Widget> inputWidgets;
	
	public NetworkTab( VBox box ) {
	
		// Create nested tabs
		nestedTabs = new Notebook();
		popBox = new VBox( false, 5 );
		nestedTabs.appendPage( popBox, new Label( "Populations") );
		projectionBox = new VBox( false, 5 );
		nestedTabs.appendPage( projectionBox, new Label( "Projections") );
		inputBox = new VBox( false, 5 );
		nestedTabs.appendPage( inputBox, new Label( "Inputs") );
		
		// Initialize widgets for tabs
		popWidgets = new ArrayList<Widget>();
		projWidgets = new ArrayList<Widget>();
		inputWidgets = new ArrayList<Widget>();
		
		// Set each tab
		setPops();
		setProj();
		setInputs();
		
		// Add nested tabs to main tab
		box.packStart( nestedTabs, false, false, 5 );
	}


	public void setPops() {
		
		// Create table layout for populations tab
		Table populationTable = new Table( 3, 3, false );
		
		// Create labels for tab
		ArrayList<Label> labels = new ArrayList<Label>();
		labels.add( new Label("Name:") );
		labels.add( new Label("Cell Type:") );
		labels.add( new Label("Population Size:") );
		labels.add( new Label("Corner (x, y, z):") );
		labels.add( new Label("Size (w, h):") );
		
		// Create widgets for tab
		popWidgets.add( new TextComboBoxEntry() );
		popWidgets.add( new Entry() );
		popWidgets.add( new Entry() );
		popWidgets.add( new HBox( false, 0 ) );
		popWidgets.add( new HBox( false, 0 ) );
		
		// Create widgets for corner parameters
		ArrayList<Entry> corner = new ArrayList<Entry>();
		corner.add( new Entry() );
		corner.add( new Entry() );
		corner.add( new Entry() );
		for( Entry item: corner ) {
			item.setSizeRequest( 40, 27 );
			((Box) popWidgets.get(3)).packStart( item, false, false, 0 );			
		}
		
		// Create widgets for size parameters
		ArrayList<Entry> size = new ArrayList<Entry>();
		size.add( new Entry() );
		size.add( new Entry() );
		for( Entry item: size ) {
			item.setSizeRequest( 40, 27 );
			((Box) popWidgets.get(4)).packStart( item, false, false, 0 );			
		}	
				
		// Attach labels to table
		for( int i = 0, j = 0, k = 1; i < labels.size(); i++, j++, k++ ) {		
			labels.get(i).setAlignment( 0.0f, 0.0f );
			populationTable.attach( labels.get(i), 0, 1, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );
		}
				
		// Attach widgets to table
		for( int i = 0, j = 0, k = 1; i < popWidgets.size(); i++, j++, k++ ) {		
			populationTable.attach( popWidgets.get(i), 1, 2, j, k, AttachOptions.FILL, AttachOptions.FILL, 40, 15 );
		}
		
		// Add table to tab
		popBox.packStart( populationTable, false, false, 5 );
	}
	
	public void setProj() {
		
		// Create table layout for projections tab
		Table projectionTable = new Table( 3, 3, false );
		
		// Create labels for tab
		ArrayList<Label> labels = new ArrayList<Label>();
		labels.add( new Label("Projections:") );
		labels.add( new Label("Units:") );
		labels.add( new Label("Source:") );
		labels.add( new Label("Target:") );
		labels.add( new Label("Synapse Type:") );
		labels.add( new Label("Internal Delay:") );
		labels.add( new Label("Wait:") );
		labels.add( new Label("Threshold:") );
		labels.add( new Label("Probability:") );
		
		// Create widgets for tab
		projWidgets.add( new TextComboBoxEntry() );
		projWidgets.add( new Entry() );
		projWidgets.add( new TextComboBoxEntry() );
		projWidgets.add( new TextComboBoxEntry() );
		projWidgets.add( new TextComboBoxEntry() );
		projWidgets.add( new Entry() );
		projWidgets.add( new Entry() );
		projWidgets.add( new Entry() );
		projWidgets.add( new Entry() );
		
		// Attach labels to table
		for( int i = 0, j = 0, k = 1; i < labels.size() / 2; i++, j++, k++ ) {
			labels.get(i).setAlignment( 0.0f, 0.0f );
			projectionTable.attach( labels.get(i), 0, 1, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );
		}
		
		for( int i = labels.size() / 2, j = 0, k = 1; i < labels.size(); i++, j++, k++ ) {
			labels.get(i).setAlignment( 0.0f, 0.0f );
			projectionTable.attach( labels.get(i), 2, 3, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );
		}
		
		// Attach widgets to table
		for( int i = 0, j = 0, k = 1; i < projWidgets.size() / 2; i++, j++, k++ ) {
			projWidgets.get(i).setSizeRequest( 140, 27 );
			projectionTable.attach( projWidgets.get(i), 1, 2, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );
		}
		
		for( int i = projWidgets.size() / 2, j = 0, k = 1; i < projWidgets.size(); i++, j++, k++ ) {
			projWidgets.get(i).setSizeRequest( 140, 27 );
			projectionTable.attach( projWidgets.get(i), 3, 4, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );
		}
		
		// Add table to tab
		projectionBox.packStart( projectionTable, false, false, 5 );
	}
	
	public void setInputs() {
		
		// Create table layout for inputs tab
		Table inputTable = new Table( 5, 3, false );
		
		// Create labels for tab
		ArrayList<Label> labels = new ArrayList<Label>();
		labels.add( new Label("Inputs:") );
		labels.add( new Label("Units:") );
		labels.add( new Label("Target Population:") );
		labels.add( new Label("Pulse Input:") );
		labels.add( new Label("Site Pattern:") );		
		
		// Create widgets for tab
		inputWidgets = new ArrayList<Widget>();
		inputWidgets.add( new TextComboBoxEntry() );
		inputWidgets.add( new Entry() );
		inputWidgets.add( new TextComboBoxEntry() );
		inputWidgets.add( new Table( 3, 2, false ) );
		inputWidgets.add( new Entry() );
		
		// Create entries for input pulse
		Entry impulseDelay = new Entry(); 
		Entry impulseDuration = new Entry();
		Entry impulseAmplitude = new Entry();
		
		// Create labels for input pulse, align left, and resize widgets
		Label delayLable = new Label( "Delay:" );
		Label durLable = new Label( "Duration:" );
		Label ampLable = new Label( "Amplitude:" );			
		delayLable.setAlignment( 0.0f, 0.0f );
		durLable.setAlignment( 0.0f, 0.0f );
		ampLable.setAlignment( 0.0f, 0.0f );
		impulseDelay.setSizeRequest( 60, 27 );
		impulseDuration.setSizeRequest( 60, 27 );
		impulseAmplitude.setSizeRequest( 60, 27 );
		
		// Attach input impulse to a table which is attached to a cell inside main table
		((Table) inputWidgets.get(3)).attach(delayLable, 0, 1, 0, 1, AttachOptions.FILL, AttachOptions.FILL, 0, 5 );
		((Table) inputWidgets.get(3)).attach( impulseDelay, 1, 2, 0, 1, AttachOptions.FILL, AttachOptions.FILL, 10, 5 );
		((Table) inputWidgets.get(3)).attach( durLable, 0, 1, 1, 2, AttachOptions.FILL, AttachOptions.FILL, 0, 5 );
		((Table) inputWidgets.get(3)).attach( impulseDuration, 1, 2, 1, 2, AttachOptions.FILL, AttachOptions.FILL, 10, 5 );
		((Table) inputWidgets.get(3)).attach( ampLable, 0, 1, 2, 3, AttachOptions.FILL, AttachOptions.FILL, 0, 5 );
		((Table) inputWidgets.get(3)).attach( impulseAmplitude, 1, 2, 2, 3, AttachOptions.FILL, AttachOptions.FILL, 10, 5 );
		
		// Attach labels to table				
		for( int i = 0, j = 0, k = 1; i < labels.size() - 1; i++, j++, k++ ) {
			labels.get(i).setAlignment( 0.0f, 0.0f );
			inputTable.attach( labels.get(i), 0, 1, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );
		}
		
		// Attach widgets to table
		for( int i = 0, j = 0, k = 1; i < inputWidgets.size() - 1; i++, j++, k++ ) {
			inputWidgets.get(i).setSizeRequest( 140, 27 );
			inputTable.attach( inputWidgets.get(i), 1, 2, j, k, AttachOptions.FILL, AttachOptions.FILL, 15, 15 );
		}

		// Add table to tab
		inputBox.packStart( inputTable, false, false, 5 );
		
		// Attach Site Pattern label and widget
		labels.get(4).setAlignment( 0.0f, 0.0f );
		inputWidgets.get(4).setSizeRequest( 140, 27 );
		HBox site = new HBox( false,5 );
		site.packStart( labels.get(4), false, false, 5 );
		site.packStart( inputWidgets.get(4), false, false, 50 );
		inputBox.packStart( site, false, false, 75 );
		
		
	}
}
