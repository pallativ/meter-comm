/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.parser.app.repositories;

import com.parser.app.entities.BillingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Veera
 */
public interface BillingHistoryRepository extends JpaRepository<BillingHistory, Long> {

}
