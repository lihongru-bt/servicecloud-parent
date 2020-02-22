package com.ltylribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

public class RandomRule_ZY extends AbstractLoadBalancerRule {

   private int total =0;//total =5 以后，指针加一
   private int currentIndex =0;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(),key);
    }
//    int total =0;//total =5 以后，指针加一
//    int index =0;
    public Server choose(ILoadBalancer lb,Object key){
        if(lb == null){
            return null;
        }
        Server server = null;
        while(server == null){
            if(Thread.interrupted()){
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if(serverCount == 0){
                return null;
            }

            if(total < 5){
                server =  upList.get(currentIndex);
                total++;
            }else{
                total = 0;
                currentIndex++;
                if(currentIndex >= upList.size()){
                    currentIndex = 0;
                }
            }

//            int index = rand.nextInt(serverCount);
//            server = upList.get(index);

            if(server == null){
                Thread.yield();
                continue;
            }

            if(server.isAlive()){
                return (server);
            }

            server = null;
            Thread.yield();
        }
        return server;

    }
}
