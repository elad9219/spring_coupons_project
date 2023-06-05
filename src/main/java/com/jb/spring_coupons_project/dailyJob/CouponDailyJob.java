package com.jb.spring_coupons_project.dailyJob;

import com.jb.spring_coupons_project.repository.CouponRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@EnableAsync
@EnableScheduling
public class CouponDailyJob {

    private CouponRepository couponRepository;


    @Async
    @Scheduled(cron = "0 0 0 * * ?", zone = "Asia/Jerusalem")  //Daily and local timezone
    public void deleteOldCoupon() {
        System.out.println("Checking for expired coupons...");
        couponRepository.deleteOldCoupons();
        System.out.println("checking was completed successfully");
    }
}

