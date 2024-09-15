package config

import org.springframework.core.io.ClassPathResource
import ru.vtvhw.scopes.ApiKey
import ru.vtvhw.scopes.GooglePlayMarket
import ru.vtvhw.scopes.MobileApp
import ru.vtvhw.scopes.RuStoreMarket

def properties = new Properties()
properties.load(new ClassPathResource('application.properties').inputStream)

def apiKeyVal= properties.getProperty('apiKey.value', 'API Key is not set!')
def marketName= properties.getProperty('market.name', 'ruStore')
def appDefaultName= properties.getProperty('mobileApp.defaultName', 'Prototype scope')

beans {
    apiKey(ApiKey, apiKeyVal) {
        bean ->
            bean.scope = "singleton"
    }

    if (marketName == 'ruStore') {
        market(RuStoreMarket, apiKey) {
            bean ->
                bean.scope = 'singleton'
                bean.lazyInit = 'true'
                bean.destroyMethod = 'doDestroy'
        }
    } else {
        market(GooglePlayMarket, apiKey) {
            bean ->
                bean.scope = 'singleton'
                bean.lazyInit = 'true'
                bean.destroyMethod = 'doDestroy'
        }
    }

    mobileApp(MobileApp, appDefaultName) {
        bean ->
            bean.scope = 'prototype'
            bean.initMethod = 'doSomeInit'
    }
}