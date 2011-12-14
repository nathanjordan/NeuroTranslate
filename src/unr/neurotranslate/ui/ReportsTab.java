package unr.neurotranslate.ui;

import java.util.ArrayList;

import org.gnome.gtk.AttachOptions;
import org.gnome.gtk.Entry;
import org.gnome.gtk.Label;
import org.gnome.gtk.Table;
import org.gnome.gtk.TextComboBox;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;

public class ReportsTab {

	private ArrayList<Widget> widgets;
	
	public ReportsTab( VBox container ) {
		
		// Set report labels
		ArrayList<Label> labels = new ArrayList<Label>();
		labels.add( new Label("Report:") );
		labels.add( new Label("Type:") );
		labels.add( new Label("ASCII:") );
		labels.add( new Label("Cells:") );
		labels.add( new Label("Filename:") );
		labels.add( new Label("Prob:") );
		labels.add( new Label("Report On:") );
		labels.add( new Label("Frequency:") );
		labels.add( new Label("Time Start:") );
		labels.add( new Label("Time End:") );
				
		// align all labels
		for( Label item: labels ) {
			item.setAlignment( 0.0f, 0.0f );			
		}
		
		// set widgets for reports
		widgets = new ArrayList<Widget>();
		widgets.add( new TextComboBox() );
		for( int i = 0; i < 9; i++ ) {
			widgets.add( new Entry("") );
			
			// Set non-editable fields
			if( i != 0 ) {
				( (Entry) widgets.get(i) ).setEditable(false);
			}									
		}
					
		// create the reports table
		Table table = new Table( 12, 4, false );
		
		// Set reports table labels	
		for( int i = 0, j = 0, k = 1; i < labels.size() / 2 + 1; i++, j++, k++ ) {								
			table.attach( labels.get(i), 0, 1, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );			
		}	
		
		
		for( int i = labels.size() / 2 + 1, j = 0, k = 1; i < labels.size(); i++, j++, k++ ) {								
			table.attach( labels.get(i), 2, 3, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );			
		}
					
		// Set reports table data 		
		for( int i = 0, j = 0, k = 1; i < widgets.size() / 2 + 1; i++, j++, k++ ) {			
			table.attach( widgets.get(i), 1, 2, j, k, AttachOptions.FILL, AttachOptions.FILL, 15, 15 );			
		}	
		
		for( int i = widgets.size() / 2 + 1, j = 0, k = 1; i < widgets.size(); i++, j++, k++ ) {			
			table.attach( widgets.get(i), 3, 4, j, k, AttachOptions.FILL, AttachOptions.FILL, 15, 15 );			
		}
				
		// Add table to container
		container.packStart( table, false, false, 5 );
		
	}
}
