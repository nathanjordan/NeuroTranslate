package unr.neurotranslate.ui;

import java.util.ArrayList;

import org.gnome.gtk.AttachOptions;
import org.gnome.gtk.Box;
import org.gnome.gtk.Entry;
import org.gnome.gtk.HBox;
import org.gnome.gtk.IconSize;
import org.gnome.gtk.Image;
import org.gnome.gtk.Label;
import org.gnome.gtk.Notebook;
import org.gnome.gtk.Stock;
import org.gnome.gtk.Table;
import org.gnome.gtk.TextComboBox;
import org.gnome.gtk.TextComboBoxEntry;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;

/**
 * The MorphologyTab class is responsible for handling all the Morphology tab layout and widgets. A Table layout is used in order to hold all the widgets used
 * in this tab. This tab creates a nested tab interface for the Cells, Segments, and Cables tab. 
 * @author nitish/kim
 */
public class MorphologyTab {
	
	// Nested tab variables
	private Notebook nestedTabs;
	
	// VBox containers for nested tabs
	private VBox cellBox;
	private VBox segmentBox;
	private VBox cableBox;
	
	// Widgets for each tab
	private ArrayList<Widget> segWidgets;
	
	public MorphologyTab( VBox box ) {
						
		// Create nested tabs
		nestedTabs = new Notebook();
		cellBox = new VBox( false, 0 );
		nestedTabs.appendPage( cellBox, new Label("Cells") );
		segmentBox = new VBox( false, 0 );
		nestedTabs.appendPage( segmentBox, new Label("Segments") );
		cableBox = new VBox( false, 0 );
		nestedTabs.appendPage( cableBox, new Label("Cables") );
				
		// Initialize widgets for each tab
		segWidgets = new ArrayList<Widget>();
		
		// Set each tab
		setCellsTab();		
		setSegsTab();		
		setCablesTab();		

		// Add nested tabs to main tab
		box.packStart( nestedTabs, false, false, 5 );
	}

	
	public void setCellsTab() {
	
		// Create table layout for cell tab
		Table cellTable = new Table( 3, 2, false );			
		
		// Combo-box for cell names
		TextComboBoxEntry cellNames = new TextComboBoxEntry();
		cellNames.setSizeRequest( 150, 27);
		
		cellNames.appendText( "Exitatory_cell");
		cellNames.appendText( "Inhibitory_cell" );
		cellNames.setActive(0);
		
		// Status icon for matching parameters
		Image mappingImg = new Image(Stock.YES, IconSize.BUTTON);	
				
		// Attach labels and widgets to table		
		cellTable.attach( new Label( "Cell Name:" ), 0, 1, 0, 1, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );
		cellTable.attach( cellNames, 1, 2, 0, 1, AttachOptions.FILL, AttachOptions.FILL, 40, 15 );
		cellTable.attach( mappingImg, 2, 3, 0, 1, AttachOptions.FILL, AttachOptions.FILL, 0, 0 );
		
		// Add table to vbox container
		cellBox.packStart( cellTable, false, false, 5 );
	}

	
	public void setSegsTab() {

		// Create table layout for segments tab
		Table segmentTable = new Table( 3, 3, false );	
			
		// Create labels
		ArrayList<Label> labels = new ArrayList<Label>();
		labels.add( new Label("Segment ID:") );		
		labels.add( new Label("Name:") );
		labels.add( new Label("Cable:") );
		labels.add( new Label("Proximal (x, y, z):") );
		labels.add( new Label("Distal (x, y, z):") );
						
		// Create widgets	
		segWidgets.add( new Entry("0") );
		segWidgets.add( new Entry("s1") );
		segWidgets.add( new Entry("CableSoma_i1") );
		segWidgets.add( new HBox( false, 0) );
		segWidgets.add( new HBox( false, 0) );
		
		// Widgets for proximal/distal
		ArrayList<Widget> xyzVals = new ArrayList<Widget>();
		for( int i = 0; i < 6; i++ ) {
			xyzVals.add( new Entry("0") );
			xyzVals.get(i).setSizeRequest( 40, 27 );
		}
		
		
		// Create green or red image boxes to indicate successful NeuroML mapping 
		ArrayList<Image> mappingImg = new ArrayList<Image>();
		for( int i = 0; i < segWidgets.size(); i++ ) {
			mappingImg.add(new Image(Stock.YES, IconSize.BUTTON) );
		}
		
		// Set proximal/distal into hboxes		
		((Box) segWidgets.get(3)).packStart(xyzVals.get(0), false, false, 0);
		((Box) segWidgets.get(3)).packStart(xyzVals.get(1), false, false, 0);
		((Box) segWidgets.get(3)).packStart(xyzVals.get(2), false, false, 0);		
		((Box) segWidgets.get(4)).packStart(xyzVals.get(3), false, false, 0);
		((Box) segWidgets.get(4)).packStart(xyzVals.get(4), false, false, 0);
		((Box) segWidgets.get(4)).packStart(xyzVals.get(5), false, false, 0);
					
		
		// Attach labels to table
		for( int i = 0, j = 0, k = 1; i < labels.size(); i++, j++, k++ ) {	
			labels.get(i).setAlignment( 0.0f, 0.0f );
			segmentTable.attach( labels.get(i), 0, 1, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );
		}
		
		// Attach widgets to table		
		for( int i = 0, j = 0, k = 1; i < segWidgets.size(); i++, j++, k++ ) {		
			segmentTable.attach( segWidgets.get(i), 1, 2, j, k, AttachOptions.FILL, AttachOptions.FILL, 30, 15 );
		}
		
		// Attach mapping icons to table		
		for( int i = 0, j = 0, k = 1; i < mappingImg.size(); i++, j++, k++ ) {		
			segmentTable.attach( mappingImg.get(i), 2, 3, j, k, AttachOptions.FILL, AttachOptions.FILL, 0, 15 );
		}
		
		// Add table to vbox container
		segmentBox.packStart( segmentTable, false, false, 5 );
	}
	
	
	public void setCablesTab() {

		// Create table layout for cables tab
		Table cableTable = new Table( 3, 3, false );			
	
		// Create labels and widgets
		ArrayList<Label> labels = new ArrayList<Label>();
		labels.add( new Label("Cable ID:") );
		labels.add( new Label("Cable Name:") );
		labels.get(0).setAlignment( 0.0f, 0.0f );
		labels.get(1).setAlignment( 0.0f, 0.0f );
		TextComboBox cableIds = new TextComboBox();
		TextComboBox cableNames = new TextComboBox();
		cableIds.setSizeRequest( 150, 27 );		
		Image mappingImg = new Image( Stock.YES, IconSize.BUTTON );
					
		cableIds.appendText( "1" );
		cableIds.setActive(0);
		cableNames.appendText( "SomaCable_i1" );
		cableNames.setActive(0);
		
		// Attach labels and widgets to table		
		cableTable.attach( labels.get(0), 0, 1, 0, 1, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );
		cableTable.attach( cableIds, 1, 2, 0, 1, AttachOptions.FILL, AttachOptions.FILL, 40, 15 );
		cableTable.attach( mappingImg, 2, 3, 0, 1, AttachOptions.FILL, AttachOptions.FILL, 0, 0 );
		cableTable.attach( labels.get(1), 0, 1, 1, 2, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );
		cableTable.attach( cableNames, 1, 2, 1, 2, AttachOptions.FILL, AttachOptions.SHRINK, 40, 15 );
		
		// Add table to vbox container
		cableBox.packStart( cableTable, false, false, 5 );	
	}
}
