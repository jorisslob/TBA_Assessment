package nl.jslob.tba.assessment.impl;

import java.time.LocalTime;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import nl.jslob.tba.assessment.model.CollisionAwareSchedule;
import nl.jslob.tba.assessment.model.Truck;

public class CollisionAwareScheduleImpl implements CollisionAwareSchedule {
	protected TreeMap<LocalTime, Truck> map;
	
	public CollisionAwareScheduleImpl() {
		map = new TreeMap<LocalTime, Truck>();
	}
	
	public CollisionAwareScheduleImpl(CollisionAwareScheduleImpl truckmap) {
		map = truckmap.map;
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public Truck get(Object key) {
		return map.get(key);
	}

	@Override
	public Truck remove(Object key) {
		return map.remove(key);
	}

	@Override
	public void putAll(Map<? extends LocalTime, ? extends Truck> m) {
		for(Entry<? extends LocalTime, ? extends Truck> e:m.entrySet()) {
			map.put(e.getKey(), e.getValue());
		}
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Set<LocalTime> keySet() {
		return map.keySet();
	}

	@Override
	public Collection<Truck> values() {
		return map.values();
	}

	@Override
	public Set<java.util.Map.Entry<LocalTime, Truck>> entrySet() {
		return map.entrySet();
	}

	@Override
	public Truck put(LocalTime lt, Truck t) {
		LocalTime time = lt;
		while(map.containsKey(time)) {
			time = time.plusNanos(1);
		}
		return map.put(time, t);
	}

	@Override
	public java.util.Map.Entry<LocalTime, Truck> pollFirstEntry() {
		return map.pollFirstEntry();
	}

}
