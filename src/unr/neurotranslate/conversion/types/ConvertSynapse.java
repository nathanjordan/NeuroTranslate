package unr.neurotranslate.conversion.types;

import org.morphml.channelml.schema.DoubleExponentialSynapse;
import org.morphml.channelml.schema.SynapseType;
import org.morphml.metadata.schema.Properties;
import org.morphml.networkml.schema.SynapseInternalProperties;

import unr.neurotranslate.ncsclasses.Synapse;
import unr.neurotranslate.ncsclasses.SynapseData;

public class ConvertSynapse {
		
	public static SynapseType convertSynapse( unr.neurotranslate.ncsclasses.Synapse ncsSynapse ) 
	{
		
		SynapseType neuroMLSynapse = new SynapseType();
		DoubleExponentialSynapse doubExpSyn = new DoubleExponentialSynapse();
		String tempString = ncsSynapse.MaxG.toString();
		double tempDouble = Double.parseDouble(tempString);
		doubExpSyn.setMaxConductance(tempDouble);
		neuroMLSynapse.setDoubExpSyn(doubExpSyn);
		
		return neuroMLSynapse;
		
	}
}
