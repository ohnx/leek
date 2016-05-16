package ca.masonx.leek.core.events;

import ca.masonx.leek.core.annotations.LeekEventHandler;
import ca.masonx.leek.core.world.Level;

/**
 * Event Handler Registerer.
 * 
 * Uses Reflection to parse the annotations and checks for the @LeekEventHandler annotation.
 */
public class EventHandlerRegister {
	/**
	 * Register the event handlers for a given class.
	 * 
	 * Sample usage (assuming called from a class that implements Listener): registerEventHandlers(this).
	 * 
	 * @param l		The level that the event handler is going to be registered under
	 * @param in	A class that wants to be notified of events.
	 */
	@SuppressWarnings("rawtypes")
	public static void registerEventHandlers(Level l, Object in) {
		Class<?> cours = in.getClass();
		if (cours.isAnnotationPresent(LeekEventHandler.class)) {
			/* get the interfaces so we can see what the class is hooking */
			Class[] interfaces = cours.getInterfaces();
			/* loop through all the interfaces */
			for (Class i : interfaces) {
				if (i.getName().equalsIgnoreCase("java.awt.event.KeyListener") ||
					i.getName().equalsIgnoreCase("java.awt.event.MouseListener") ||
					i.getName().equalsIgnoreCase("java.awt.event.MouseMotionListener") ||
					i.getName().equalsIgnoreCase("ca.masonx.leek.core.CollisionListener")) {
					l.em.addEventHandler(in);
					return;
				}
			}
			System.err.println(in.getClass().getName() + " is not a valid event handler! Ignoring.");
		}
	}
}
