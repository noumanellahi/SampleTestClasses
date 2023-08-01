package com.mycompany.sigar;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.ptql.ProcessFinder;

public class SigarTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Request recieved");
			Sigar sigar = new Sigar();
			ProcessFinder find = new ProcessFinder(sigar);
			long[] pids = find.find("Exe.Name.re=^.*\\\\chrome(.)*.exe$");
			for (Long pid : pids) {
				System.out.println(sigar.getProcState(pid));
				System.out.println(sigar.getProcState(pid));

//				sigar.kill(pid, Sigar.getSigNum("KILL"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
