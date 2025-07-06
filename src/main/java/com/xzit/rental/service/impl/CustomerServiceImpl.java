package com.xzit.rental.service.impl;

import com.xzit.rental.entity.Customer;
import com.xzit.rental.mapper.CustomerMapper;
import com.xzit.rental.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author teacher_shi
 * @since 2025-07-04
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}
