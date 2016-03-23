USE [twchenan]
GO
/****** Object:  StoredProcedure [dbo].[check_struc_p]    Script Date: 03/23/2016 15:26:49 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[check_struc_p]
(
  @people varchar(30) ,
  @result varchar(30) OUTPUT
)
as
begin
declare  @GG varchar(30),@jgypbh varchar(30),@resultstr varchar(30)
declare cur_ypbh cursor for 
   select twchenan.dbo.Zsm022.FYPBH  from twchenan.dbo.zsm022 where twchenan.dbo.zsm022.FCREATER = @people
   open cur_ypbh
   while(@@Fetch_Status=0)
   begin
	  SET @GG = ''
      Fetch Next From cur_ypbh into @GG
        IF @GG <>''  AND @GG IS NOT NULL
        BEGIN
		select @jgypbh=twchenan.dbo.Zsm002.FCPBH from twchenan.dbo.Zsm002 where twchenan.dbo.Zsm002.FCPBH = @GG
		 if @jgypbh is null OR  @jgypbh = ''
		 begin
		  if @result is not null 
		       begin
		           SET @result = @result+','+@GG
		       end
		   else
		      begin
		           SET @result =@GG
		      end
		 end
	     SET @jgypbh = ''
	     END
   end
   loving:
	  close cur_ypbh
	  DEALLOCATE cur_ypbh
end
GO
