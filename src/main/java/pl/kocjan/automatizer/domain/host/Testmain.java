package pl.kocjan.automatizer.domain.host;

import pl.kocjan.automatizer.domain.common.LocalCommandRunner;

public class Testmain {

	public static void main(String[] args) {
		LocalCommandRunner cr = new LocalCommandRunner();
		HostCreator hc = new HostCreator(null, cr);
		
		int result = hc.registerServerKeyOnHost("7675277", "kacor11", "localhost");
		
		System.out.println("result is " + result);

	}
	
}
