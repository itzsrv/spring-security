### Spring Security

Security needs lot of thought.

We have to build multiple layers of security, and even anticipate that some of them might get hacked/breached.

Application Security is not fun.

Security is often an afterthought.

Security potentially causes user frustration.

My Web Server has security builtin, why should I as Application Developer need to deal with security.


You need to think what are the things that users can do, in the context of application. 
What's the functionality that you want to allow to whom. 
What's the kind of data you want to expose and to whom.

That's the kind of security we are talking about.

The biggest thing that make security a challenge is, security threats are constantly evolving and morphing.

So, basically you want to hire a kind of watchman, who stands in front and intercepts any visitors.
Anytime somebody wants to enter, security guard asks them two quintessential questions asked by security guards over the years, all over the world
- who are you?
- what do you want?

Spring security is liking hiring a fulltime security guard, who sits in front of your web application and examines every request to your application and asks these two basic questions.

Spring security is an application framework, that helps you do application level security!
What does that mean?
A bunch of things actually.

- you can say, hey spring security, give me login and logout functionality.
- i want only these two urls to be publicly accessable, these 2 urls only logged in users to access, this particular url accessable to someone not only logged in but also have admin access.
this is access control specific to your application.

How does it do it?
Spring security lets you **configure** it to your application's needs. So you can have it do the right things that you want.

because you see the need for security itself is very common, but how does that security needs to be achieved is not common, it varies widely between applications. For this reason, spring security has been built to have a lot of flexibility.
It is very likely, it will be able to handle your particular security needs.

another benefit of spring security is that it can handle a lot of the common security vulnerabilities right out of the box. just by adding spring security to your app, you get protection from a bunch of security vulnerabilities, like
- session fixation
- click jacking
- cross site request forgery and all that stuff.

other benefit of spring security is, that its very widely adopted in the industry.

but since its so widely adopted, people are constantly trying to find vulnerabilities in it. However, considering the usage, even if the vulnerability is found, it will immediately get patched.


### Few things that you can do with Spring Security:
- username password authentication
- SSO / Okta / LDAP
- App Level Authorization
- Intra App Authorization like OAuth (tech: Apps authorized with other Apps)
- Microservice security (using tokens, JWT) (basically when you have bunch of microservices that are talking to each other, how do you make sure that one microservice exposes certain apis only for this one  microservice and not others)
- method level security (securing methods and objects in your code, more granular and popular)


## 5 Core Concepts in Spring Security
1. Authentication
1. Authorization
1. Principal
1. Granted Authority
1. Roles


### Authentication

Who are you?
Proof - usually some kind of id

Most web applications have some representation of an id, (facebook or linkedin - have id and account in their system that uniquely corresponds to you, user id and password, you give your account first that exists in app, then authenticate yourself with a password)

this type of authentication is called knowledge based authentication
- password
- pin code
- personal question
- or some other secret 

advantage its simple and pretty effective for the most part, 
disadvantage, if someone steals or finds your password, they can impersonate, at the end its single string value that uniquely identifies you 

there are other authentication mechanisms like possesion based authentication
app texts you a message
key card to enter into fielding by swiping

MFA, combination of Knowledge Based and Possession Based Authentication

### Authorization

What do you want (to do)?
this allow yes or no decision, depending upon who the user is and what they are trying to do is called authorization. 

So, in a way for Authorization, you kind of need Authentication first.

### Principal

A principal is the currently logged in user. It is that unique information or account in the system that you tie to a specific person in the context of the application.

Once you authenticate with any application, say by giving your user name and password, the application establishes the principal and it remembers it. This is the reason why you authenticate with an application only once and you dont need to enter your userid and password for every request or on every page load. 

### Granted Authority

We looked at how authentication happens, knowledge based or possession based. 

But, how does authorization happens?
How does application decide whether or not to allow a particular access. Well, that has to be coded in before hand obviously. 
The application owner or maintainer should have specified beforehand, like
- if user is this, allow them to use only these actions
- if user is this, allow them only these actions

its like a bunch of permissions that are allowed for a given user.

In Spring Security is concept of permission is called Authority or Granted Authority. 

Authorization --> Authority

Fine grained permissions of what user can do. You can configure these authorities in Spring Security for user. And Spring Security takes over the rest. 


Here is a thing with Granted Authority, they are too fine grained.
If a new Store Manager comes up, or if few Store Clerks are hired then each one of them have to be assigned all the authority specifically. This is very tedious.

This is where you create the concept of Role.

## Roles

Role is like a group of authorities, that are usually assigned together. 
- role_store_clerk
- role_dept_manager
- role_store_manager

Roles are more coarse-grained permissions, unlike that fine grained permissions that Authorities have. 

In Spring Security, both these concepts of Roles and Authority are used interchangeably.


Add Spring Security to your application by adding the starter security to maven pom.xml

spring-boot-starter-security

/login

./login?error

How??


The answer to this is by using,

## Filters

Filters are very basic concept with Servlet Application. And Spring Boot and Spring Security and all of these java web application are, Servlets Application afterall.

So you have basic servlet technology, working underneath to provide all this kind of rich funtionality, and you have all these frameworks built on top this, so you dont have to deal with servlet layer. But what's actually doing all the job are those servlet technology.
And filters are one of the core concepts associated with Servlet technologies. 

Filters stand right in the middle and they intercept every request, and this gives you opportunity to do something with every request. (cross cutting functionality)
- log every request
- check every request if header is there or not

--> Spring Security Filter


### Spring Security default behaviour

- Adds mandatory authentication for all(except few like error pages) urls (by default without any url)
- Adds a login form
- Handles login error

with default user 'user' and password 'random passwords'

Overwrite the default user and password by adding below config in applicaion.properties file

spring.security.user.name=foo
spring.security.user.password=bar


### How to configure Authentication using Spring Security

The way to configure Authentication in Spring Security is by affecting what's called is Authentication Manager.

It manages the authentication in Spring Security Application. It has a mehtod called authenticate(). That either returns a successful authentication or an exception that I can not authenticate.

Authentication Manager is what does the authentication. Now, how do you affect it?
The way to affect it is not by creating your own Authentication Manager, but instead to configure what Authentication Manager does using a builder pattern, AuthenticationManagerBuilder.

This is a two step process:
1. get hold of AuthenticationManagerBuilder
1. set the configuration on it

so, you can imagine the interaction with AuthenticationManagerBuilder being that configuration.

While dealing with AuthenticationManagerBuilder, the first thing it's going to ask you is,
- What type of authentication do you want?
suppose you say, inmemory authentication
- Ok, tell me then what's the username, password and the role of your inmemory users are?
you give your <userinfo>, (it could be one or multiple users)

once you have done this, once you have configured AuthenticationManagerBuilder with these properties, you can imagine, a new AuthenticationManager being created somehow which has the values that you want. so you are not directly dealing with am, you are dealing with AuthenticationManagerBuilder.

How do you get hold of AuthenticationManagerBuilder?
The way to do is by leveraging a hook that is already available in the Spring Security App, the thing is, in Spring Security App, there is a class that's sitting there which has a method called configure and it takes in as an argument, the AuthenticationManagerBuilder and the spring security framework calls that configure method and passes in the authentication manager builder. the reason that class is there so that it gives the developers an opportunity to extend the class, override the configure method and do the configuration that you want. If you dont extend this class and override the method, the default configuration happens. However, if you were to just extend this class and override this method, now you have the ability to write the method which takes the AuthenticationManagerBuilder as an argument, and once you put this in code, spring security is going to call your configured method and pass the AuthenticationManagerBuilder. Now that's an opportunity for you to take AuthenticationManagerBuilder instance and do this interaction that we talked about. 


### How to configure authorization in Spring Security

We will learn to enable or disable access to api depending upon who the logged in user is. 

You can configure spring security authorization to do a gazzilion different things. But we will learn here the way to configure authorization.

The way to achieve authorization is by using an Object of type HttpSecurity. HttpSecurity lets you configure the paths and access restrictions.

How do you get hold of this object HttpSecurity on which you put your Authorization configuration? 
by using the WebSecurityConfigurerAdapter.

most restrictive to least restrictive

### how does Spring Security Authentication work?

1. How Spring Security bootstraps itself to even begin to do any work?
2. How does it authenticate and decide that the user are who they say they are? 


You don't invoke the SS, you dont call its apis, you add dependencies of SSStarter in your SpringBoot Application, and the framework starts intercepting requests right out of gate.

How does it do it?
The way spring security adds itself to the request processing is by adding filter to your app.

A filter is essentially a construct in servlet application that lets you intercept request. 

Say, a request is coming in that needs to be disptatched to  a certain servlet. Well, you can create a bunch of filters and say, before this request is handled by the servlets, it has to go through these filters first.  

The filter has the opportunity to do any processing or manipulate the request before it goes to the servlet and infact it can stop certain requests so that they dont even reach servlet. There is usually a one to one mapping between a url and a servlet method. One url is typically handled by one servlet method. But filters can be applied to a wide range of urls, for example you can say map this filter to all URLs that start with admin/** or for example, apply this filter to all urls in this application using /**. 

 So in the case of web application that's what Spring Security does. When you drop the SSStarter dependency into your spring boot app, it does the filter mapping to intercept all the requests /** and maps it to Spring Security's own filter called delegating filter proxy. 

 If you are not working in a spring boot app, you'll manually need to add this filter to intercept all requests. So looking at that code will give you an idea of what's happening behind the scenes. 

 ```xml
<filter>
    <filter-name>springSecurtiyFilterChain</filter-name>
    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>

<filter-mapping>
    <filter-name>springSecurtiyFilterChain</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
 ```

 You dont need to do this because of the default configuration when working with Spring Boot Applications in the SSStarter dependency. But if you are working in a non spring boot app, or you need to add this manually, then this is what you need to add in your web.xml.

 What if, I want to add authorization to only one url of my app, the rest of the url dont need to be accessible by everyone, so there's no access control restrictions, so why should I add SS to have it intercept each and every URL. I wanted to just look at just this one URL or a set of URLs.

 The reason is that for an app to be secure it has to be protected from all logon abilities and you can focus on just a handful of urls that you need authentication for. If you add SS to just one part of your application, and let other part of your application have vulnerabilities, well then your entire application is not secure.

 That's the basic idea, you intercept all the requests with SS at the filter level and then you tell SS what your authentication and authorization requirements are per url. 

 That way all your configuration is directly with Spring Security and you dont have to mess with filters after that, irrespective of whether you want one url in your Application to be secured by SS or multiple urls. 

 DelegatingFilerProxy
    its a catch all filter that spring security adds as a starting point is actually a delegating filter to other SS specific filters to do different things depending upon the url being requested.

Irrespective of the configuration, there are around 5 or 6 filters that SS provides out of the box. One of these filter is Authentication which intercepts all Authentication Requests and initiates the authentication process. As you can imagine, there are several authorization related filters as well, and even filters to skip authorization for like static files in the web application things css or js files. 

### How does Spring Security do what it does (specifically around authentication )?

There is an Authentication Filter that intercepts authentication requests and initiate the process of authentication but what happens after that.

Well think of authentication as an operation with inputs and outputs, 
what are the inputs to an authentication and what are the outputs?

typically the inputs are the credentials from the user who is trying to authenticate, for example userid and password

the output could be a boolean, yes correct credentials true or no incorrect credentials false. But in reality, its little more than that.

When the authentication is successful, the authentication process returns the Principal (the information about the logged in user).

When Spring Security performs authentication, it keeps track of both, the input and the output using an object of type authentication. Autentication is an internal SS interface and the auth object meant to hold credentials before authentication. And once user is authenticated it holds the principle. You can think of auth as this data transfer object for authentication. credentials before authentication and holder of principle after the auth is successful. 

What's the thing that does the actual authentication?
There are several ways in which this can be done in spring security application, but the most common pattern you will find is by using **Providers**. 

So there is something called as an authentication provider. Think of auth provider as something responsible for doing the actual authentication. So this is an interface that has a method called authenticate and you need to have implementations of this interface, implementation of this authenticate method in your application and then tell SS about it. SS will then call this authenticate method to authenticate your users. 


AuthenticationProvider
    authenticate()

-->
input
Authentication Object having
credentials


<--
output 
Authentication Object having
Principal

A single application can have multiple Authentication Strategies (userid pass, oauth, ldap/sso based authentication).

So as a result, an application can have multiple Authentication Providers. Each one knowing how to authenticate with a specific authentication mechanism.

How do these different Authenticaton Providers work with each other?
In order to coordinate all of them, there is a special type in Spring Security called the Authentication Manager.

Authentication Manager
    authenticate()


There are different ways in which you can implement this AuthenticationManager, but common implementation that we are looking at here is the providere managers, Provider Pattern.

Which does this kind of delegation to authentication providers depending on whichever provider supports the authentication

AuthenticationProvider
    authenticate()
    **supports()**

In order to identify, the AuthenticationProvider has to be able to lookup the identitystore. So it has access to UserDetailsService

Interface UserDetails



Authenticaton object is saved by AuthenticationFilter in the thread context. Well there is a security context that is associated with current thread. This Auth Object is put into Security Context in ThreadLocal object for use authorization in identifying the current principal and more.  


there is also a mechanism to allow for this context to be associated with it, the session. This work is done by another filter
 












