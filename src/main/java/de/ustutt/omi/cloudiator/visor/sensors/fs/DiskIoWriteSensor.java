/*
 * Copyright (c) 2015 University of Stuttgart
 *
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package de.ustutt.omi.cloudiator.visor.sensors.fs;

import org.hyperic.sigar.DiskUsage;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;

import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementNotAvailableException;
import de.ustutt.omi.cloudiator.visor.sensors.AbstractDiskIoSensor;

public class DiskIoWriteSensor extends AbstractDiskIoSensor {

	public double getUsage(final String name, final double unit)
			throws MeasurementNotAvailableException {
		try {
			SigarProxy proxy = SigarProxyCache.newInstance();
			DiskUsage du = proxy.getDiskUsage(name);
			double dataPerSecond = du.getWrites() / unit;
			return Math.floor(dataPerSecond * 100) / 100;
		} catch (Exception e) {
			throw new MeasurementNotAvailableException(e);
		}
	}

}
