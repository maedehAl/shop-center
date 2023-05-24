package com.sadad.co.ir.api.shopcenter.integrations;

import com.sadad.co.ir.api.shopcenter.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class WalletProviderImp implements WalletProvider {

    @Value("${wallet.balance-url}")
    private String walletBalanceUrl;

    private final WebClient webClient;
    private final OrderRepository orderRepository;
    public  WalletDto getBalance(String snn){
               return webClient.get()
                .uri(walletBalanceUrl+"/"+snn)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                //.body(Mono.just("hhhh"),String.class)
                .retrieve()
                .bodyToMono(WalletDto.class)
                .block();
    }

//    @Override
//    public PayWalletDtoResp payWithWallet(Integer orderId) {
////        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order Not Found"));
////
////        WalletDto balance = getBalance(orderEntity.getCustomer().getSnn());
////
////        if (balance.getBalances()>orderEntity.getTotalAmount()){
////            return null;
////        }
////        PayWalletDtoResp payWalletDtoResp = new PayWalletDtoResp();
////        payWalletDtoResp.setStatus(OrderStatus.PAID);
////        payWalletDtoResp.setRemainAmount(balance.getBalances());
//////        orderEntity.getTotalAmount();
//        return payWalletDtoResp;
//    }


}
