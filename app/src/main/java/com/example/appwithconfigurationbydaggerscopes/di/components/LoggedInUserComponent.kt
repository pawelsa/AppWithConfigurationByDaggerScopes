package com.example.appwithconfigurationbydaggerscopes.di.components

import com.example.appwithconfigurationbydaggerscopes.di.scopes.LoggedInUserScope
import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent

@LoggedInUserScope
@DefineComponent(parent = SingletonComponent::class)
interface LoggedInUserComponent {

    @DefineComponent.Builder
    interface Builder {
        fun build(): LoggedInUserComponent
    }

}