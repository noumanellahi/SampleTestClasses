package com.mycompany.thread;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class ThreadExecutorWithFuture {
	public static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("Downloader-%d")
			.setDaemon(true).build();
	public static ExecutorService executor = Executors.newFixedThreadPool(10, threadFactory);
	public static ExecutorService mainExecutor = Executors.newFixedThreadPool(10, threadFactory);

	public static void main(String[] args) throws InterruptedException {
		for (int i = 1; i <= 50; i++) {
			System.out.println("Source call : " + i);
			mainExecutor.execute(new ProcessMessageTask(i));
		}
		Thread.sleep(150000);
	}

	static class ProcessMessageTask implements Runnable {
		int iterator;

		ProcessMessageTask(int i) {
			iterator = i;
		}

		@Override
		public void run() {
			System.out.println("Source revieved : " + iterator);
			Callable<Boolean> task = () -> {
				System.out.println("Source run : " + iterator);
				test(iterator);
				return true;
			};
			Future<Boolean> future = executor.submit(task);

			try {
				future.get(10, TimeUnit.SECONDS);
				System.out.println("Source complete : " + iterator);
			} catch (Exception ex) {
				future.cancel(Boolean.TRUE);
				System.out.println("Time out for iterator : " + iterator);
			}
		}
	}

	public static void test(int i)
			throws MalformedURLException, IOException, InterruptedException, ExecutionException, TimeoutException {
		ExecutorService mainExecutor = Executors.newSingleThreadExecutor();
		try {
			Callable<Boolean> mainTask = () -> {
				while (true) {
					Thread.sleep(0);
				}
			};
			Future<Boolean> mainFuture = mainExecutor.submit(mainTask);
			mainFuture.get(4, TimeUnit.SECONDS);
		} catch (Exception ex) {
			System.out.println("Kill the thread : " + i);
		}
	}

}
