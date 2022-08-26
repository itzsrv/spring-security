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

### Roles

Role is like a group of authorities, that are usually assigned together. 
- role_store_clerk
- role_dept_manager
- role_store_manager

Roles are more coarse-grained permissions, unlike that fine grained permissions that Authorities have. 

In Spring Security, both these concepts of Roles and Authority are used interchangeably.

 












