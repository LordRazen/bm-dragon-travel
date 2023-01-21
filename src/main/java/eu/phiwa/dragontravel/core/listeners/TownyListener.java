package eu.phiwa.dragontravel.core.listeners;

import com.palmergames.bukkit.towny.event.MobRemovalEvent;
import eu.phiwa.dragontravel.core.DragonTravel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class TownyListener implements Listener
{
	/*
	 * This prevents Towny from Removing DragonTravel dragons that enter a removal region from outside
	 */
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEnderDragonRemoval(MobRemovalEvent e)
	{
		if(e.getEntityType() != null){
			if (!e.getEntity().getType().toString().equals("ENDER_DRAGON"))
				return;

			if (e.isCancelled())
				return;

			if (DragonTravel.getInstance().getConfigHandler().isIgnoreAntiMobspawnAreas())
			{
				if(DragonTravel.getInstance().getDragonManager().isDragonTravelDragon(e.getEntity())){
					e.setCancelled(true);
				}		
			}			
		}
	}
}