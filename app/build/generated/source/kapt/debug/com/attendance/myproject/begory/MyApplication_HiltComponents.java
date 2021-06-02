package com.attendance.myproject.begory;

import androidx.hilt.lifecycle.ViewModelFactoryModules;
import com.attendance.myproject.begory.di.module.AppModule;
import com.attendance.myproject.begory.presentationLayer.login.LoginActivity_GeneratedInjector;
import com.attendance.myproject.begory.presentationLayer.login.LoginViewModel_HiltModule;
import com.attendance.myproject.begory.presentationLayer.main.MainActivity_GeneratedInjector;
import com.attendance.myproject.begory.presentationLayer.main.MainViewModel_HiltModule;
import com.attendance.myproject.begory.presentationLayer.main.NotificationsFragment_GeneratedInjector;
import com.attendance.myproject.begory.presentationLayer.main.attendence.AttendanceFragment_GeneratedInjector;
import com.attendance.myproject.begory.presentationLayer.main.attendence.takeAttendance.LevelAttendActivity_GeneratedInjector;
import com.attendance.myproject.begory.presentationLayer.main.attendence.takeAttendance.LevelAttendViewModel_HiltModule;
import com.attendance.myproject.begory.presentationLayer.main.changePassword.PasswordActivity_GeneratedInjector;
import com.attendance.myproject.begory.presentationLayer.main.changePassword.PasswordViewModel_HiltModule;
import com.attendance.myproject.begory.presentationLayer.main.profile.ProfileFragment_GeneratedInjector;
import com.attendance.myproject.begory.presentationLayer.main.profile.ProfileViewModel_HiltModule;
import com.attendance.myproject.begory.presentationLayer.main.setting.SettingsFragment_GeneratedInjector;
import com.attendance.myproject.begory.presentationLayer.main.setting.addBlaBla.AddBlaBlaActivity_GeneratedInjector;
import com.attendance.myproject.begory.presentationLayer.main.setting.addBlaBla.AddBlaBlaViewModel_HiltModule;
import com.attendance.myproject.begory.presentationLayer.main.setting.showTakedAttendance.ShowLevelAttendActivity_GeneratedInjector;
import com.attendance.myproject.begory.presentationLayer.main.setting.showTakedAttendance.ShowLevelAttendViewModel_HiltModule;
import com.attendance.myproject.begory.presentationLayer.main.setting.updateBlaBla.UpdateBlaBlaActivity_GeneratedInjector;
import com.attendance.myproject.begory.presentationLayer.main.setting.updateBlaBla.UpdateBlaBlaViewModel_HiltModule;
import com.attendance.myproject.begory.presentationLayer.main.setting.userExist.UserActivity_GeneratedInjector;
import com.attendance.myproject.begory.presentationLayer.main.setting.userExist.UserViewModel_HiltModule;
import com.attendance.myproject.begory.presentationLayer.main.shop.ShopFragment_GeneratedInjector;
import com.attendance.myproject.begory.presentationLayer.main.shop.ShopViewModel_HiltModule;
import com.attendance.myproject.begory.presentationLayer.splash.SplashActivity_GeneratedInjector;
import com.attendance.myproject.begory.presentationLayer.splash.SplashViewModel_HiltModule;
import dagger.Binds;
import dagger.Component;
import dagger.Module;
import dagger.Subcomponent;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.components.ServiceComponent;
import dagger.hilt.android.components.ViewComponent;
import dagger.hilt.android.components.ViewWithFragmentComponent;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.managers.ActivityComponentManager;
import dagger.hilt.android.internal.managers.FragmentComponentManager;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_LifecycleComponentBuilderEntryPoint;
import dagger.hilt.android.internal.managers.ServiceComponentManager;
import dagger.hilt.android.internal.managers.ViewComponentManager;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.HiltWrapper_ActivityModule;
import dagger.hilt.android.scopes.ActivityRetainedScoped;
import dagger.hilt.android.scopes.ActivityScoped;
import dagger.hilt.android.scopes.FragmentScoped;
import dagger.hilt.android.scopes.ServiceScoped;
import dagger.hilt.android.scopes.ViewScoped;
import dagger.hilt.internal.GeneratedComponent;
import dagger.hilt.migration.DisableInstallInCheck;
import javax.annotation.Generated;
import javax.inject.Singleton;

@Generated("dagger.hilt.processor.internal.root.RootProcessor")
public final class MyApplication_HiltComponents {
  private MyApplication_HiltComponents() {
  }

  @Module(
      subcomponents = ActivityC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ActivityCBuilderModule {
    @Binds
    ActivityComponentBuilder bind(ActivityC.Builder builder);
  }

  @Module(
      subcomponents = ActivityRetainedC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ActivityRetainedCBuilderModule {
    @Binds
    ActivityRetainedComponentBuilder bind(ActivityRetainedC.Builder builder);
  }

  @Module(
      subcomponents = FragmentC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface FragmentCBuilderModule {
    @Binds
    FragmentComponentBuilder bind(FragmentC.Builder builder);
  }

  @Module(
      subcomponents = ServiceC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ServiceCBuilderModule {
    @Binds
    ServiceComponentBuilder bind(ServiceC.Builder builder);
  }

  @Module(
      subcomponents = ViewC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewCBuilderModule {
    @Binds
    ViewComponentBuilder bind(ViewC.Builder builder);
  }

  @Module(
      subcomponents = ViewWithFragmentC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewWithFragmentCBuilderModule {
    @Binds
    ViewWithFragmentComponentBuilder bind(ViewWithFragmentC.Builder builder);
  }

  @Subcomponent(
      modules = {
          DefaultViewModelFactories.ActivityModule.class,
          HiltWrapper_ActivityModule.class,
          FragmentCBuilderModule.class,
          ViewCBuilderModule.class,
          ViewModelFactoryModules.ActivityModule.class
      }
  )
  @ActivityScoped
  public abstract static class ActivityC implements LoginActivity_GeneratedInjector,
      MainActivity_GeneratedInjector,
      LevelAttendActivity_GeneratedInjector,
      PasswordActivity_GeneratedInjector,
      AddBlaBlaActivity_GeneratedInjector,
      ShowLevelAttendActivity_GeneratedInjector,
      UpdateBlaBlaActivity_GeneratedInjector,
      UserActivity_GeneratedInjector,
      SplashActivity_GeneratedInjector,
      ActivityComponent,
      DefaultViewModelFactories.ActivityEntryPoint,
      FragmentComponentManager.FragmentComponentBuilderEntryPoint,
      ViewComponentManager.ViewComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          AddBlaBlaViewModel_HiltModule.class,
          LevelAttendViewModel_HiltModule.class,
          LoginViewModel_HiltModule.class,
          MainViewModel_HiltModule.class,
          ActivityCBuilderModule.class,
          PasswordViewModel_HiltModule.class,
          ProfileViewModel_HiltModule.class,
          ShopViewModel_HiltModule.class,
          ShowLevelAttendViewModel_HiltModule.class,
          SplashViewModel_HiltModule.class,
          UpdateBlaBlaViewModel_HiltModule.class,
          UserViewModel_HiltModule.class
      }
  )
  @ActivityRetainedScoped
  public abstract static class ActivityRetainedC implements ActivityRetainedComponent,
      ActivityComponentManager.ActivityComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityRetainedComponentBuilder {
    }
  }

  @Component(
      modules = {
          AppModule.class,
          ApplicationContextModule.class,
          ActivityRetainedCBuilderModule.class,
          ServiceCBuilderModule.class
      }
  )
  @Singleton
  public abstract static class ApplicationC implements MyApplication_GeneratedInjector,
      ApplicationComponent,
      HiltWrapper_ActivityRetainedComponentManager_LifecycleComponentBuilderEntryPoint,
      ServiceComponentManager.ServiceComponentBuilderEntryPoint,
      GeneratedComponent {
  }

  @Subcomponent(
      modules = {
          DefaultViewModelFactories.FragmentModule.class,
          ViewWithFragmentCBuilderModule.class,
          ViewModelFactoryModules.FragmentModule.class
      }
  )
  @FragmentScoped
  public abstract static class FragmentC implements NotificationsFragment_GeneratedInjector,
      AttendanceFragment_GeneratedInjector,
      ProfileFragment_GeneratedInjector,
      SettingsFragment_GeneratedInjector,
      ShopFragment_GeneratedInjector,
      FragmentComponent,
      DefaultViewModelFactories.FragmentEntryPoint,
      ViewComponentManager.ViewWithFragmentComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends FragmentComponentBuilder {
    }
  }

  @Subcomponent
  @ServiceScoped
  public abstract static class ServiceC implements ServiceComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ServiceComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewC implements ViewComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewWithFragmentC implements ViewWithFragmentComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewWithFragmentComponentBuilder {
    }
  }
}
