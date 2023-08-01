package com.mycompany.gson;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GetInformationFromObject {

	public static void main(String[] args) {
		try {
			// TODO Auto-generated method stub
			String info = "{\"DockerId\":\"1af2784c625446acb937741e33c71449-3526828777\",\"Name\":\"webacq-scraper-container\",\"DockerName\":\"webacq-scraper-container\",\"Image\":\"425353067356.dkr.ecr.us-west-2.amazonaws.com/webacq-scraper:latest\",\"ImageID\":\"sha256:df209e782615a2fe0ffbcedb19826ba966db3f05de34198223ff080d3191abbd\",\"Labels\":{\"com.amazonaws.ecs.cluster\":\"arn:aws:ecs:us-west-2:425353067356:cluster/webacq-dev\",\"com.amazonaws.ecs.container-name\":\"webacq-scraper-container\",\"com.amazonaws.ecs.task-arn\":\"arn:aws:ecs:us-west-2:425353067356:task/webacq-dev/1af2784c625446acb937741e33c71449\",\"com.amazonaws.ecs.task-definition-family\":\"webacq-scraper-task-definition\",\"com.amazonaws.ecs.task-definition-version\":\"276\"},\"DesiredStatus\":\"RUNNING\",\"KnownStatus\":\"RUNNING\",\"Limits\":{\"CPU\":2},\"CreatedAt\":\"2021-12-14T06:48:08.021003009Z\",\"StartedAt\":\"2021-12-14T06:48:08.021003009Z\",\"Type\":\"NORMAL\",\"Volumes\":[{\"DockerName\":\"webacq-scraper-task-definition-276-Files\",\"Destination\":\"/efs\"}],\"Networks\":[{\"NetworkMode\":\"awsvpc\",\"IPv4Addresses\":[\"172.31.40.73\"],\"AttachmentIndex\":0,\"MACAddress\":\"06:9f:74:39:66:05\",\"IPv4SubnetCIDRBlock\":\"172.31.32.0/20\",\"DomainNameServers\":[\"172.31.0.2\"],\"DomainNameSearchList\":[\"us-west-2.compute.internal\"],\"PrivateDNSName\":\"ip-172-31-40-73.us-west-2.compute.internal\",\"SubnetGatewayIpv4Address\":\"172.31.32.1/20\"}],\"ContainerARN\":\"arn:aws:ecs:us-west-2:425353067356:container/webacq-dev/1af2784c625446acb937741e33c71449/adacc9ec-3bfc-45c3-9f59-8ee9177cf9bc\",\"LogOptions\":{\"awslogs-group\":\"/ecs/webacq-scraper-task-definition\",\"awslogs-region\":\"us-west-2\",\"awslogs-stream\":\"ecs/webacq-scraper-container/1af2784c625446acb937741e33c71449\"},\"LogDriver\":\"awslogs\"}";
			JsonObject jsonObject = JsonParser.parseString(info).getAsJsonObject();
			System.out.println(jsonObject.getAsJsonArray("Networks").get(0).getAsJsonObject()
					.getAsJsonArray("IPv4Addresses").getAsJsonArray().get(0).toString());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
