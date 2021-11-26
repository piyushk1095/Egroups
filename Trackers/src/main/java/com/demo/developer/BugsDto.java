package com.demo.developer;

import com.demo.developer.model.Bugs;
import com.demo.developer.model.Stories;

public class BugsDto {

	public Bugs statusBugs(Bugs bugs) {

		if (bugs.getStatus().equalsIgnoreCase("NEW") || bugs.getStatus().equalsIgnoreCase("Completed")
				|| bugs.getStatus().equalsIgnoreCase("Estimated"))

		{
			return bugs;

		} else {
			bugs.setStatus(null);

			return bugs;
		}
	}

	public Stories statusStrories(Stories stories) {

		if (stories.getStatus().equalsIgnoreCase("NEW") || stories.getStatus().equalsIgnoreCase("VERIFIED")
				|| stories.getStatus().equalsIgnoreCase("Resollved"))

		{
			return stories;

		}
		stories.setStatus(null);

		return stories;
	}

	public Bugs priotyBugs(Bugs bugs) {

		if (bugs.getPriority().equalsIgnoreCase("Critical") || bugs.getPriority().equalsIgnoreCase("Major")
				|| bugs.getPriority().equalsIgnoreCase("Minor"))

		{
			return bugs;

		}

		bugs.setPriority(null);

		return bugs;
	}

}
